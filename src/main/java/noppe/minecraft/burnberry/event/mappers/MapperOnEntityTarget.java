package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.event.events.EventEntityTarget;
import noppe.minecraft.burnberry.event.events.EventPlayerJoin;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MapperOnEntityTarget extends CustomEventMapper{
    public MapperOnEntityTarget(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event){
        EventEntityTarget ev = new EventEntityTarget(event);
        if (ev.entity != null){
            ev.entity.onEntityTarget(event, ev);
        }
    }
}
