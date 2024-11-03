package noppe.minecraft.burnberry.event.events;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.GameMode;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EventInventoryClick extends CustomEvent{
    public CustomPlayer player;
    public ItemStack itemClicked;

    public EventInventoryClick(InventoryClickEvent event) {
        super(event);
        player = (CustomPlayer) M.getWrapper(event.getWhoClicked());
        this.itemClicked = event.getCurrentItem();
        event.setCancelled(!player.playerWrapped.getGameMode().equals(GameMode.CREATIVE));
    }
}
