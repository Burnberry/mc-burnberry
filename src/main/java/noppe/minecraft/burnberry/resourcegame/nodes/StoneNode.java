package noppe.minecraft.burnberry.resourcegame.nodes;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.helpers.R;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceNode;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class StoneNode extends ResourceNode {
    static ItemStack stone = new ItemStack(Material.STONE);
    static ItemStack weakStone = new ItemStack(Material.COBBLESTONE);
    public boolean hasIron=false;

    public StoneNode(MiniGame game, int slot, int health){
        super(game, slot, health);
        DamagePerResource = 3;
        damageRes = Res.STONE;
        if (R.randomInt(7, 20) <= health){
            hasIron = true;
        }
        rewardRes = Res.STONE;
    }

    @Override
    public void onHit(CustomPlayer player) {
        onDamage(game.game.upgrades.pickaxePower);
        setItem(getItem());
        playSound(player, Sound.BLOCK_STONE_BREAK);
        if (isFinished()){
            remove();
        }
    }

    @Override
    public ItemStack getItem() {
        if (health > 3){
            return stone;
        }
        else if (health > 0){
            return weakStone;
        }
        return null;
    }

    @Override
    public void remove() {
        super.remove();
        if (hasIron){
            game.addResource(Res.IRON, 1);
            game.nodes.add(new DisplayNode(game, slot, 0, new ItemStack(Material.RAW_IRON)));
        }
    }
}
