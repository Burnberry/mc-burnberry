package noppe.minecraft.burnberry.resourcegame.nodes;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceNode;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class LeafNode extends ResourceNode {
    TreeNode tree;

    public LeafNode(MiniGame game, int slot, TreeNode tree) {
        super(game, slot, 0);
        tree.leafNodes.add(this);
    }

    @Override
    public void onHit(CustomPlayer player) {
        return;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Material.OAK_LEAVES);
    }
}
