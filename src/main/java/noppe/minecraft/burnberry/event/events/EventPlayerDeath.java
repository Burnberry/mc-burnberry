package noppe.minecraft.burnberry.event.events;

import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventPlayerDeath extends CustomEvent{
    public CustomPlayer player;

    public EventPlayerDeath(PlayerDeathEvent event) {
        super(event);
        player = (CustomPlayer) M.getWrapper(event.getEntity());
    }
}
