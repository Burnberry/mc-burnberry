package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.resourcegame.minigames.MiningGame;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ResourceGame extends CustomEventListener {
    public CustomPlayer player;
    public MiningGame miningGame;

    public ResourceGame(CustomPlayer player){
        this.player = player;
        miningGame = new MiningGame(this);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev) {
        super.onInventoryClick(event, ev);
        miningGame.onSlotClicked(event.getSlot());
    }

    public void reload(){
        player.playerWrapped.openInventory(miningGame.getInventory());
    }
}
