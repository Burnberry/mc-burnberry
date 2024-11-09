package noppe.minecraft.burnberry.resourcegame.minigames;

import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.nodes.LeafNode;
import noppe.minecraft.burnberry.resourcegame.nodes.StoneNode;
import noppe.minecraft.burnberry.resourcegame.nodes.TreeNode;
import noppe.minecraft.burnberry.resourcegame.nodes.WoodNode;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ForageGame extends MiniGame {
    public TreeNode tree;

    public ForageGame(ResourceGame game) {
        super(game, "Forest");
    }

    @Override
    public void setNodes() {
        M.print(inventory.toString());
        nodes = new ArrayList<>();
        tree = new TreeNode(this, 7);

        nodes.add(new WoodNode(this, 49, tree));
        nodes.add(new WoodNode(this, 40, tree));
        nodes.add(new WoodNode(this, 31, tree));
        nodes.add(new WoodNode(this, 22, tree));

        nodes.add(new LeafNode(this, 24, tree));
        nodes.add(new LeafNode(this, 23, tree));
        nodes.add(new LeafNode(this, 21, tree));
        nodes.add(new LeafNode(this, 20, tree));

        nodes.add(new LeafNode(this, 15, tree));
        nodes.add(new LeafNode(this, 14, tree));
        nodes.add(new LeafNode(this, 13, tree));
        nodes.add(new LeafNode(this, 12, tree));
        nodes.add(new LeafNode(this, 11, tree));

        nodes.add(new LeafNode(this, 3, tree));
        nodes.add(new LeafNode(this, 4, tree));
        nodes.add(new LeafNode(this, 5, tree));

    }

    @Override
    public void viewMinigame() {
        game.viewForest();
    }
}
