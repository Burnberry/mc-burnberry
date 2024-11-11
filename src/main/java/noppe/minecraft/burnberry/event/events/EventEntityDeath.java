package noppe.minecraft.burnberry.event.events;

import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

public class EventEntityDeath extends CustomEvent{
    public CustomEntity entity;
    public CustomPlayer player;

    public EventEntityDeath(EntityDeathEvent event) {
        super(event);
        entity = M.getWrapper(event.getEntity());
        player = M.getPlayerFromDamageSource(event);
    }
}
