package noppe.minecraft.burnberry.entities;

import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.event.events.EventPlayerInteract;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.item.Menu;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.view.View;
import noppe.minecraft.burnberry.view.views.ResourceView;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class CustomPlayer extends CustomEntity{
    public Player playerWrapped;

    public View view;
    private final int menuUseCooldown = 10;
    public int lastMenuUseTime = -menuUseCooldown;
    public ResourceGame game;

    public CustomPlayer(CustomEventListener origin, Entity entity) {
        super(origin, entity);
        playerWrapped = (Player) entity;
        game = new ResourceGame(this);
    }

    public void onTick(){
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
        } else if (game != null) {
            game.onInventoryClick(event, ev);
        }
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event, EventPlayerInteract ev) {
        if (ev.rightClick && ev.item != null){
            if (M.matches(Menu.startGame, ev.item)){
                setGameView();
            }
        }
    }

    public void setGameView(){
        game.reload();
    }
}
