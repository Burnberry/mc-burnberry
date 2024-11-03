package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.EventHandler;
import noppe.minecraft.burnberry.Burnberry;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MapperOnInventoryClick extends CustomEventMapper {
    public MapperOnInventoryClick(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        EventInventoryClick ev =  new EventInventoryClick(event);
        ev.player.onInventoryClick(event, ev);
    }
}
