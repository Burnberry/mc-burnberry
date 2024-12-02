package noppe.minecraft.burnberry.entities;

import noppe.minecraft.burnberry.Lobby;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.event.events.EventPlayerDeath;
import noppe.minecraft.burnberry.event.events.EventPlayerInteract;
import noppe.minecraft.burnberry.gui.RadialItemMenu;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.item.Menu;
import noppe.minecraft.burnberry.location.Loc;
import noppe.minecraft.burnberry.view.View;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomPlayer extends CustomEntity{
    public Player playerWrapped;

    public View view;
    private final int menuUseCooldown = 10;
    public int lastMenuUseTime = -menuUseCooldown;
    public Lobby lobby;

    public RadialItemMenu radialItemMenu;

    public CustomPlayer(Lobby lobby, Entity entity) {
        super(lobby, entity);
        playerWrapped = (Player) entity;
        this.lobby = lobby;
    }

    public boolean useMenu(){
        if (this.lastMenuUseTime + this.menuUseCooldown <= M.getTicks()){
            this.lastMenuUseTime = M.getTicks();
            return true;
        }
        M.print("Wait before using a menu again");
        return false;
    }

    public void fullHeal(){
        this.updateMaxHealth();
        this.playerWrapped.setHealth(this.getMaxHealth());
        this.playerWrapped.setFoodLevel(20);
        this.playerWrapped.setSaturation(20);
    }

    public void heal(double health){
        health += this.playerWrapped.getHealth();
        health = Math.min(health, this.getMaxHealth());
        this.playerWrapped.setHealth(health);
    }

//    public void addMana(double addedMana){
//        mana += addedMana;
//        mana = Math.min(Math.max(mana, 0.0), maxMana);
//        this.updateAirManaDisplay();
//    }

//    public void updateAirManaDisplay(){
//        airManaDisplay = (int)(mana)*30 - 25;
//    }

    public Boolean isPlayer(){
        return true;
    }

    public String getName(){
        return this.playerWrapped.getName();
    }

    void updateMaxHealth(){
        Objects.requireNonNull(this.playerWrapped.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);
    }

    public double getMaxHealth(){
        return Objects.requireNonNull(this.playerWrapped.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
    }

    public void setRespawnLocation(Location location){
        this.playerWrapped.setRespawnLocation(location);
    }

    public boolean canMoveItems(){
        return (this.view == null);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){
        if (this.view != null){
            this.view.onInventoryClick(event, ev);
        } else if (lobby != null && lobby.game != null) {
            lobby.game.onInventoryClick(event, ev);
        }
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event, EventPlayerInteract ev) {
        if (ev.rightClick && ev.item != null){
            if (M.matches(Menu.startGame, ev.item)){
                lobby.startGame();
            } else if (M.matches(Menu.resourceMenu, ev.item)){
                setGameView();
            } else if (M.matches(Menu.controlGamemode, ev.item)){
                switchGameMode();
            } else if (M.matches(Menu.controlSpawnMonster, ev.item)){
                lobby.game.spawnMonsterTest(Loc.golemSpawn);
//                lobby.game.spawnMonsterTest(playerWrapped.getLocation());
//                lobby.game.spawnMonsterTest();
            } else if (M.matches(Menu.controlSkipWave, ev.item) && lobby.game != null) {
                lobby.game.wave.end();
            } else if (M.matches(Menu.controlRadialMenu, ev.item)){
                List<ItemStack> items = new ArrayList<>();
                items.add(new ItemStack(Material.GOLDEN_SWORD));
                items.add(new ItemStack(Material.GOLDEN_SHOVEL));
                items.add(new ItemStack(Material.GOLDEN_PICKAXE));
                items.add(new ItemStack(Material.GOLDEN_AXE));
                items.add(new ItemStack(Material.GOLDEN_HOE));
                radialItemMenu = new RadialItemMenu(this, items);
            } else if (event.getClickedBlock() != null && event.getClickedBlock().getBlockData().getMaterial() == Material.FLETCHING_TABLE && lobby.game != null && !ev.offHand){
                lobby.game.onPlayerGetArrows(ev.player);
            }
        }
    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event, EventPlayerDeath ev) {
        if (lobby.game != null){
            playerWrapped.setGameMode(GameMode.SPECTATOR);
        }
    }

    public void setGameView(){
        lobby.game.viewMainMenu(this);
    }

    public void switchGameMode(){
        if (playerWrapped.getGameMode() == GameMode.CREATIVE){
            playerWrapped.setGameMode(GameMode.ADVENTURE);
        }
        else {
            playerWrapped.setGameMode(GameMode.CREATIVE);
        }
    }
}
