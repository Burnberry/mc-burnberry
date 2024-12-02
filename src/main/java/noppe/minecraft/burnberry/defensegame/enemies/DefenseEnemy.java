package noppe.minecraft.burnberry.defensegame.enemies;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.entities.enemies.CustomEnemy;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventEntityDeath;
import noppe.minecraft.burnberry.event.events.EventEntityTarget;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class DefenseEnemy extends CustomEnemy {
    public DefenseGame game;
    public int souls = 1;
    public int miasma;

    public DefenseEnemy(DefenseGame game, Mob monster, int miasma) {
        super(game, monster);
        this.game = game;
        this.miasma = miasma;
    }

    public void onTick(){
        updateTarget();
    }

    public void updateTarget(){
        if (getMob().getTarget() == null){
            getMob().setTarget(game.anchor);
        }
    }

    @Override
    public void onEntityTarget(EntityTargetEvent event, EventEntityTarget ev) {
        super.onEntityTarget(event, ev);
        if (ev.target instanceof DefenseEnemy){
            event.setCancelled(true);
        }
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev) {
        super.onEntityDeath(event, ev);
        if (ev.entity instanceof DefenseEnemy){
            game.monsters.remove(ev.entity);
        }
    }
}
