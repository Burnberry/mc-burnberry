package noppe.minecraft.burnberry.resourcegame.research;

import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import noppe.minecraft.burnberry.resourcegame.util.CostPair;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ResearchQuiver extends Research{
    public ResearchQuiver() {
        super(200, 1);
    }

    @Override
    protected boolean _isAvailable() {
        return level == 0;
    }

    @Override
    protected void _onResearched() {
        game.upgrades.arrowCapacity = 24;
    }

    @Override
    public void updateCosts() {
        costs = new ArrayList<>();
        costs.add(new CostPair(Res.STONE, 10));
    }

    @Override
    protected ItemStack _getItem() {
        return new ItemStack(Material.BOW);
    }

    @Override
    public String getName() {
        return "Quiver";
    }
}
