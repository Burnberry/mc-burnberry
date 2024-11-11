package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.event.events.EventProjectileHit;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class MapperOnProjectileHit extends CustomEventMapper{
    public MapperOnProjectileHit(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event){
        new EventProjectileHit(event);
    }
}
