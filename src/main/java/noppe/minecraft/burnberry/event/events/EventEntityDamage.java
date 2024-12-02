package noppe.minecraft.burnberry.event.events;

import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventEntityDamage extends CustomEvent{
    public CustomEntity entity;

    public EventEntityDamage(EntityDamageEvent event) {
        super(event);
        entity = M.getWrapper(event.getEntity());
    }
}
