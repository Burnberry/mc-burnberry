package noppe.minecraft.burnberry.defensegame;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.entities.enemies.CustomEnemy;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.location.Loc;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class DefenseGame extends CustomEventListener {
    public CustomPlayer player;
    public ResourceGame resourceGame;

    public List<CustomEnemy> monsters;
    public Monster anchor;

    public DefenseGame(CustomPlayer player){
        this.player = player;
        resourceGame = new ResourceGame(player);

        monsters = new ArrayList<>();
        spawnAnchor();
    }

    public void clean(){
        anchor.remove();
        for (CustomEnemy enemy: monsters){
            enemy.remove();
        }
    }

    public void onTick(){
//        spawnZombie();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){
        resourceGame.onInventoryClick(event, ev);
    }

    public void viewMainMenu(){
        resourceGame.viewMainMenu();
    }

    public void spawnZombie(Location location){
        CustomEnemy enemy = new CustomEnemy(this, (Enemy) M.spawnEntity(this, location, Skeleton.class));
        monsters.add(enemy);

        enemy.getEnemy().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 1, false, false));
        Monster monster = (Monster) enemy.getEnemy();
        monster.setTarget(anchor);
    }

    public void spawnZombie(){
        spawnZombie(Loc.monsterSpawn);
    }

    public void spawnAnchor(){
        anchor = (Vex) M.spawnEntity(this, Loc.tower.clone().add(0, -0.1, 0), Vex.class);
        anchor.setAI(false);
        anchor.setSilent(true);
        anchor.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.1);
    }
}
