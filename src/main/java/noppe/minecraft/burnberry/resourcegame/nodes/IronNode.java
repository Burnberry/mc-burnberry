package noppe.minecraft.burnberry.resourcegame.nodes;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceNode;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class IronNode extends ResourceNode {
    public IronNode(MiniGame game, int slot, int health) {
        super(game, slot, health);
        DamagePerResource = 3;
        damageRes = Res.STONE;
        rewardRes = Res.IRON;
    }

    @Override
    public void onHit(CustomPlayer player) {
        onDamage(game.game.upgrades.pickaxePower);
        setItem(getItem());
        playSound(player, Sound.BLOCK_STONE_BREAK);
        if (isFinished()){
            game.addResource(Res.IRON, 1);
            remove();
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Material.IRON_ORE);
    }
}
