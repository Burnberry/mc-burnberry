package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.event.events.EventPlayerJoin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class MapperOnPlayerJoin extends CustomEventMapper{
    public MapperOnPlayerJoin(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        burnberry.game.onPlayerJoin(event, new EventPlayerJoin(event));
    }
}
