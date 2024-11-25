package noppe.minecraft.burnberry.event.events;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class EventPlayerInteract extends CustomEvent{
    public ItemStack item;
    public CustomPlayer player;
    public boolean leftClick = false;
    public boolean rightClick = false;
    public boolean offHand = false;

    public EventPlayerInteract(PlayerInteractEvent event) {
        super(event);
        player = (CustomPlayer) M.getWrapper(event.getPlayer());
        if(Objects.requireNonNull(event.getHand()).name().equals("HAND")){
            item = event.getPlayer().getInventory().getItemInMainHand();
        }
        if(Objects.requireNonNull(event.getHand()).name().equals("OFF_HAND")){
            item = event.getPlayer().getInventory().getItemInOffHand();
            offHand = true;
        }

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){
            leftClick = true;
        }
        else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            rightClick = true;
        }
    }
}
