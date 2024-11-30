package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.event.events.EventEntityDeath;
import noppe.minecraft.burnberry.event.events.EventPlayerDeath;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class MapperOnPlayerDeath extends CustomEventMapper{
    public MapperOnPlayerDeath(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityDeath(PlayerDeathEvent event){
        EventPlayerDeath ev = new EventPlayerDeath(event);
        if (ev.player != null){
            ev.player.onPlayerDeath(event, ev);
        }
    }
}
