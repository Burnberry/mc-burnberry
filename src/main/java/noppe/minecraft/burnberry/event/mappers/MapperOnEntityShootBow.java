package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.event.events.EventPlayerShootBow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;

public class MapperOnEntityShootBow extends CustomEventMapper{
    public MapperOnEntityShootBow(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event){
        EventPlayerShootBow ev = new EventPlayerShootBow(event);
        if (ev.player != null && burnberry.lobby.game != null){
            burnberry.lobby.game.onPlayerShootBow(event, ev);
        }
    }
}
