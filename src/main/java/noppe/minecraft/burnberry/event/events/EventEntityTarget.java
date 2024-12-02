package noppe.minecraft.burnberry.event.events;

import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityTargetEvent;

public class EventEntityTarget extends CustomEvent{
    public CustomEntity entity;
    public CustomEntity target;

    public EventEntityTarget(EntityTargetEvent event) {
        super(event);
        entity = M.getWrapper(event.getEntity());
        if (event.getTarget() != null){
            target = M.getWrapper(event.getTarget());
        }
    }
}
