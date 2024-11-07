package noppe.minecraft.burnberry.resourcegame.upgrades;

import jdk.internal.net.http.common.Pair;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class upgradePickaxe extends Upgrade{

    @Override
    public void onBuy(ResourceGame game) {
        game.upgrades.pickaxePower += 1;
    }

    @Override
    public List<CostPair> getCosts() {
        List<CostPair> costs = new ArrayList<>();
        if (level == 0){
            costs.add(new CostPair(Res.STONE, 3));
        } else if (level == 1) {
            costs.add(new CostPair(Res.STONE, 15));
        } else if (level == 2) {
            costs.add(new CostPair(Res.STONE, 35));
        } else if (level == 3) {
            costs.add(new CostPair(Res.STONE, 100));
        }
        return costs;
    }

    @Override
    public ItemStack _getItem() {
        if (level == 0){
            return new ItemStack(Material.WOODEN_PICKAXE);
        }
        else if (level == 1){
            return new ItemStack(Material.STONE_PICKAXE);
        }
        else if (level == 2){
            return new ItemStack(Material.IRON_PICKAXE);
        }
        else if (level == 3){
            return new ItemStack(Material.DIAMOND_PICKAXE);
        }
        return null;
    }

    @Override
    public boolean isAvailable() {
        return level < 4;
    }
}
