package noppe.minecraft.burnberry.resourcegame.crafting;

import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import noppe.minecraft.burnberry.resourcegame.util.CostPair;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ArrowRecipe extends Recipe{
    public ArrowRecipe() {
        super("Arrows");
    }

    @Override
    public void onBuy(ResourceGame game, int n) {
        game.resources.get(Res.ARROWS).addAmount(n*amount);
    }

    @Override
    public void updateCosts() {
        costs = new ArrayList<>();
        costs.add(new CostPair(Res.STONE, 1));
        costs.add(new CostPair(Res.WOOD, 1));
    }

    @Override
    protected ItemStack _getItem() {
        return new ItemStack(Material.ARROW);
    }

    @Override
    public String getName() {
        return "Arrows";
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public int capacityAvailable() {
        return 1;
    }

    @Override
    public void updateAmount() {
        amount = 4;
    }
}
