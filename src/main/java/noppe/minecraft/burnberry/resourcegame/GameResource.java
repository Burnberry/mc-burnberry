package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.inventory.ItemStack;

public class GameResource {
    public int amount;
    public ItemStack item;
    public String name;

    public GameResource(int amount, ItemStack item, String name){
        this.name = name;
        this.amount = amount;
        setItem(item);
    }

    public void setAmount(int amount){
        this.amount = amount;
        M.setItemName(item, name + ' ' + amount);
    }

    public void addAmount(int amount){
        setAmount(this.amount + amount);
    }

    public void setItem(ItemStack item) {
        this.item = item;
        setAmount(amount);
    }
}
