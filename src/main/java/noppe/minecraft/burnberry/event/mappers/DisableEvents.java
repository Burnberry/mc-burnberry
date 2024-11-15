package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
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
}
