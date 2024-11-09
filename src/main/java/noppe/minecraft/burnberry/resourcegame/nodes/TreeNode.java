package noppe.minecraft.burnberry.resourcegame.nodes;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.ResourceNode;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TreeNode{
    public int health;
    public MiniGame game;
    List<ResourceNode> woodNodes;
    List<ResourceNode> leafNodes;

    public TreeNode(MiniGame game, int health) {
        this.game = game;
        this.health = health;
        woodNodes = new ArrayList<>();
        leafNodes = new ArrayList<>();
    }

    public void onHit(ResourceNode node, CustomPlayer player){
        int damage = Math.min(health, game.game.upgrades.pickaxePower);
        game.game.resources.get(Res.WOOD).addAmount(damage);
        health -= damage;
        node.playSound(player, Sound.BLOCK_WOOD_BREAK);
        if (isFinished()){
            remove();
        }
    }

    public boolean isFinished() {
        return health <= 0;
    }

    public void remove() {
        for (ResourceNode node: leafNodes){
            node.remove();
        }
        for (ResourceNode node: woodNodes){
            node.remove();
        }
    }
}
