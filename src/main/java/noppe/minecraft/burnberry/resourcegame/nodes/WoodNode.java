package noppe.minecraft.burnberry.resourcegame.nodes;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceNode;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WoodNode extends ResourceNode {
    TreeNode tree;
    public WoodNode(MiniGame game, int slot, TreeNode tree) {
        super(game, slot, 0);
        this.tree = tree;
        tree.woodNodes.add(this);
    }

    @Override
    public void onHit(CustomPlayer player) {
        tree.onHit(this, player);
    }

    @Override
    public boolean isFinished() {
        return tree.isFinished();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Material.OAK_WOOD);
    }
}
