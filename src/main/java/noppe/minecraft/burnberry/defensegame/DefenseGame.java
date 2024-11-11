package noppe.minecraft.burnberry.defensegame;

import noppe.minecraft.burnberry.defensegame.enemies.DefenseEnemy;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.entities.enemies.CustomEnemy;
import noppe.minecraft.burnberry.defensegame.enemies.DefenseZombie;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventEntityDeath;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.location.Loc;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefenseGame extends CustomEventListener {
    public CustomPlayer player;
    public ResourceGame resourceGame;

    public List<CustomEnemy> monsters;
    public LivingEntity anchor;
    public List<Location> spawnPoints;

    public int miasma = -100;
    public int miasmaRate = 1;

    public DefenseGame(CustomPlayer player){
        this.player = player;
        resourceGame = new ResourceGame(player);

        setSpawnPoints();
        monsters = new ArrayList<>();
        spawnAnchor();
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
        for (CustomEnemy enemy: monsters){
            enemy.remove();
        }
    }

    public void onTick(){
        miasma += miasmaRate;
        if (miasma >= 0){
            spawnMonsters();
        }
        Location l = anchor.getLocation();
        for (Entity entity: M.getWorld().getNearbyEntities(new BoundingBox(l.getX()-0.5, l.getY()-0.5, l.getZ()-3, l.getX()+0.5, l.getY()+0.5, l.getZ()+3))){
            if (entity != anchor && !(entity instanceof Player)){
                entity.remove();
            }
        }

    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){
        resourceGame.onInventoryClick(event, ev);
    }

    public void viewMainMenu(){
        resourceGame.viewMainMenu();
    }

    public void spawnMonsters(){
        int n = ThreadLocalRandom.current().nextInt(2, 6);
        for (int i=0; i<n; i++){
            spawnZombie();
            miasma -= 50;
        }
    }

    public void spawnZombie(Location location){
        CustomEnemy enemy = new DefenseZombie(this, location);
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
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev) {
        if (ev.player != null){
            resourceGame.resources.get(Res.SOUL).addAmount(((DefenseEnemy) ev.entity).souls);
        }
    }

    public Location getRandomSpawn(){
        int i = ThreadLocalRandom.current().nextInt(0, spawnPoints.size());
        return spawnPoints.get(i);
    }
}
