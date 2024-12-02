package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.defensegame.enemies.DefenseEnemy;
import noppe.minecraft.burnberry.event.events.EventEntityDeath;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class MapperOnEntityDeath extends CustomEventMapper{
    public MapperOnEntityDeath(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        EventEntityDeath ev = new EventEntityDeath(event);
        if (ev.entity instanceof DefenseEnemy){
            ev.entity.onEntityDeath(event, ev);
        }
    }
}
