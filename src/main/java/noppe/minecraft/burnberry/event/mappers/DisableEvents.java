package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class DisableEvents extends CustomEventMapper {
    public DisableEvents(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void disableFood(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void disableItemDrops(PlayerDropItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void disableDurabilityLoss(PlayerItemDamageEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void disableMeleeHits(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            event.setCancelled(true);
        }
    }

}
