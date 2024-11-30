package noppe.minecraft.burnberry.defensegame;

import noppe.minecraft.burnberry.Lobby;
import noppe.minecraft.burnberry.defensegame.enemies.*;
import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.entities.enemies.CustomEnemy;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventEntityDeath;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.event.events.EventPlayerShootBow;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.location.Loc;
import noppe.minecraft.burnberry.resourcegame.GameResource;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefenseGame extends CustomEventListener {
    public Lobby lobby;
    public ResourceGame resourceGame;

    public Dictionary<CustomPlayer, DefensePlayerState> playerStates;

    public List<CustomEnemy> monsters;
    public LivingEntity anchor;
    public ItemDisplay anchorDisplay;
    public double alpha=0;
    public List<Location> spawnPoints;

    public int waveDelay = -100;
    public int waveRate = 1;
    public int baseHealth = 5;
    public int healDelay = 0;
    public int healOffset = 20;

    public DefenseGame(Lobby lobby){
        this.lobby = lobby;

        playerStates = new Hashtable<>();
        for (CustomPlayer player: lobby.players){
            onNewPlayer(player);
        }

        resourceGame = new ResourceGame(this);

        setSpawnPoints();
        monsters = new ArrayList<>();
        spawnAnchor();
    }

    public void onNewPlayer(CustomPlayer player){
        playerStates.put(player, new DefensePlayerState(player));
    }

    private void setSpawnPoints() {
        spawnPoints = new ArrayList<>();
        spawnPoints.add(new Location(M.getWorld(), 50.5, 100, -7.5));
        spawnPoints.add(new Location(M.getWorld(), 50.5, 100, 7.5));
        spawnPoints.add(new Location(M.getWorld(), 55.5, 100, -2.5));
        spawnPoints.add(new Location(M.getWorld(), 55.5, 100, 3.5));
    }

    public void clean(){
        anchor.remove();
        anchorDisplay.remove();
        for (CustomEnemy enemy: monsters){
            enemy.remove();
        }
    }

    public void onTick(){
        healDelay -= 1;

        waveDelay += waveRate;
        if (waveDelay >= 0){
            spawnMonsters();
        }
        Location l = anchor.getLocation();
        for (Entity entity: M.getWorld().getNearbyEntities(new BoundingBox(l.getX()-5, l.getY()-5, l.getZ()-5, l.getX()+0.5, l.getY()+10, l.getZ()+10))){
            CustomEntity ent = M.getWrapper(entity);
            if (ent instanceof CustomEnemy){
                entity.remove();
                onBaseDamaged(1);
            } else if (ent instanceof CustomPlayer && healDelay <= 0) {
                ((CustomPlayer) ent).heal(1);
            }
        }
        if (healDelay <= 0){
            healDelay = healOffset;
        }

        alpha += Math.PI/100;
        Transformation transform = new Transformation(new Vector3f(0, 0, 0), new AxisAngle4f((float)alpha, 0f, 1f, 0f), new Vector3f(0.5f, .5f, 0.5f), new AxisAngle4f(0f, 0f, 0f, 0f));
        anchorDisplay.setTransformation(transform);

    }


    public void onBaseDamaged(int damage){
        baseHealth -= damage;
        if (baseHealth <= 0){
            onGameLose();
        }
    }

    public void onGameLose(){
        M.print("LOST: base destroyed");
        lobby.stopGame();
    }

    @Override
    public void onPlayerShootBow(EntityShootBowEvent event, EventPlayerShootBow ev) {
        DefensePlayerState playerState = playerStates.get(ev.player);
        if (playerState == null){
            return;
        }
        if (playerState.hasArrow()){
            playerState.useArrow();
        } else{
            event.setCancelled(true);
        }
        M.print("Arrows: " + playerState.arrowCount + '/' + playerState.arrowCapacity);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){
        resourceGame.onInventoryClick(event, ev);
    }

    public void viewMainMenu(CustomPlayer player){
        resourceGame.viewMainMenu(player);
    }

    public void spawnMonsters(){
        int n = ThreadLocalRandom.current().nextInt(2, 6);
        for (int i=0; i<n; i++){
            spawnZombie();
            waveDelay -= 50;
        }
    }

    public void spawnZombie(Location location){
        CustomEnemy enemy = new DefenseWeakZombie(this, location);
        enemy.setTarget(anchor);
        monsters.add(enemy);
    }

    public void spawnZombie(){
        spawnZombie(getRandomSpawn());
    }

    public void spawnAnchor(){
        anchor = (LivingEntity) M.spawnEntity(this, Loc.tower.clone().add(3, 0.5, 0), Vex.class);
        anchor.setAI(false);
        anchor.setSilent(true);
        anchor.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.1);
        anchor.setRemoveWhenFarAway(false);

        anchorDisplay = (ItemDisplay) M.spawnEntity(anchor, anchor.getLocation().clone().add(0, 1, 0), ItemDisplay.class);
        anchorDisplay.setItemStack(new ItemStack(Material.GOLDEN_SWORD));
        anchorDisplay.setVelocity(new Vector(1, 0, 0));
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev) {
        if (ev.player != null && ev.entity != null){
            resourceGame.resources.get(Res.SOUL).addAmount(((DefenseEnemy) ev.entity).souls);
        }
    }

    public Location getRandomSpawn(){
        int i = ThreadLocalRandom.current().nextInt(0, spawnPoints.size());
        return spawnPoints.get(i);
    }

    public void onPlayerGetArrows(CustomPlayer player){
        GameResource arrows = resourceGame.resources.get(Res.ARROWS);
        int taken = playerStates.get(player).addArrows(arrows.amount);
        arrows.addAmount(-taken);
        if (taken > 0){
            player.playerWrapped.playSound(player.playerWrapped, Sound.ITEM_WOLF_ARMOR_REPAIR, 1, 1);
        }
    }
}
