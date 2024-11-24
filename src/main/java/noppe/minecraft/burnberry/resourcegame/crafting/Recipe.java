package noppe.minecraft.burnberry.resourcegame.crafting;

import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.util.Spendable;
import org.bukkit.inventory.ItemStack;

public abstract class Recipe extends Spendable {
    public int leftover = 0;
    public int amount;
    public String name;

    public Recipe(String name){
        super();
        this.name = name;
    }

    public abstract void onBuy(ResourceGame game, int amount);

    @Override
    public void onBuy(ResourceGame game) {
        onBuy(game, 1);
    }

    @Override
    public int amountCanBuy(ResourceGame game) {
        return Math.min(super.amountCanBuy(game), capacityAvailable());
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = super.getItem();
        setItemName(item);
        return item;
    }

    public void setItemName(ItemStack item){
        String itemName = name;

        itemName += " +" + amount;
        if (leftover > 0){
            itemName += " (+" + leftover + ")";
        }

        M.setItemName(item, itemName);
    }

    public abstract int capacityAvailable();

    @Override
    public void update() {
        super.update();
        updateAmount();
    }

    public void updateAmount(){
        amount = 1;
    }
}
