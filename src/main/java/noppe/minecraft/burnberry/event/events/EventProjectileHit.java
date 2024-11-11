package noppe.minecraft.burnberry.event.events;

import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EventProjectileHit extends CustomEvent{
    public CustomEntity entity;
    public CustomPlayer player;

    public EventProjectileHit(ProjectileHitEvent event) {
        super(event);
        if (event.getHitEntity() != null && event.getEntity().getShooter() instanceof Player){
            Player p = ((Player) event.getEntity().getShooter());
            p.playSound(p, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
        }
    }
}
