package noppe.minecraft.burnberry.resourcegame.nodes;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceNode;
import org.bukkit.inventory.ItemStack;

public class DisplayNode extends ResourceNode {
    public boolean removeOnHit=true;
    public ItemStack displayItem;

    public DisplayNode(MiniGame game, int slot, int health, ItemStack displayItem, boolean removeOnHit) {
        super(game, slot, health);
        this.removeOnHit = removeOnHit;
        this.displayItem = displayItem;
        setItem(displayItem);
    }

    public DisplayNode(MiniGame game, int slot, int health, ItemStack displayItem) {
        super(game, slot, health);
        this.displayItem = displayItem;
        setItem(displayItem);
    }

    @Override
    public void onHit(CustomPlayer player) {
        if (removeOnHit){
            remove();
        }
    }

    @Override
    public ItemStack getItem() {
        return displayItem;
    }
}
