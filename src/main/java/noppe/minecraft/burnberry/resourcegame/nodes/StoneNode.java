package noppe.minecraft.burnberry.resourcegame.nodes;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceNode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class StoneNode extends ResourceNode {
    static ItemStack stone = new ItemStack(Material.STONE);
    static ItemStack weakStone = new ItemStack(Material.COBBLESTONE);

    public StoneNode(MiniGame game, int slot, int health){
        super(game, slot, health);
    }

    @Override
    public void onHit(CustomPlayer player) {
        health -= 1;
        setItem(getItem());
        playSound(player, Sound.BLOCK_STONE_BREAK);
        if (isFinished()){
            remove();
        }
    }

    @Override
    public ItemStack getItem() {
        if (health > 1){
            return stone;
        }
        else if (health == 1){
            return weakStone;
        }
        return null;
    }
}
