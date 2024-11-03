package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventPlayerInteract;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class MapperOnPlayerInteract extends CustomEventMapper{
    public MapperOnPlayerInteract(Burnberry plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        CustomEntity player = M.getWrapper(event.getPlayer());

        if (player == null){
            return;
        }
        EventPlayerInteract ev = new EventPlayerInteract(event);
        player.onPlayerInteract(event, ev);
    }
}
