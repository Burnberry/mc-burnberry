package noppe.minecraft.burnberry.event.events;

import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.EntityShootBowEvent;

public class EventPlayerShootBow extends CustomEvent{
    public CustomPlayer player;

    public EventPlayerShootBow(EntityShootBowEvent event) {
        super(event);
        CustomEntity entity = M.getWrapper(event.getEntity());
        if (entity instanceof CustomPlayer){
            player = (CustomPlayer) entity;
            ((Arrow)event.getProjectile()).setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        }
    }
}
