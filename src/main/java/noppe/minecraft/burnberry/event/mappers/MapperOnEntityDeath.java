package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.event.events.EventEntityDeath;
import noppe.minecraft.burnberry.event.events.EventPlayerJoin;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MapperOnEntityDeath extends CustomEventMapper{
    public MapperOnEntityDeath(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        burnberry.game.onEntityDeath(event, new EventEntityDeath(event));
    }
}
