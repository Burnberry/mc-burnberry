package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.event.events.EventEntityDamage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class MapperOnEntityDamage extends CustomEventMapper{
    public MapperOnEntityDamage(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        EventEntityDamage ev = new EventEntityDamage(event);
        if (ev.entity != null){
            ev.entity.onEntityDamage(event, ev);
        }
    }
}
