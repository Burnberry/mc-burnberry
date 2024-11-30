package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.location.Loc;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

public class BasicMapper extends CustomEventMapper{
    public BasicMapper(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        event.setRespawnLocation(Loc.spawn);
    }
}
