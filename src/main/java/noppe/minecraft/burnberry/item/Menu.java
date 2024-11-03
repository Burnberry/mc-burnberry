package noppe.minecraft.burnberry.item;

import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class Menu {
    public static ItemStack black = Menu.createBlack();
    public static ItemStack startGame = Menu.createStartGame();
    public static ItemStack controlLeft = Menu.createControlLeft();
    public static ItemStack controlRight = Menu.createControlRight();

    static ItemStack createBlack(){
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        M.setItemName(itemStack, "");
        M.setItemNBTName(itemStack, "Black");
        return itemStack;
    }

    static ItemStack createStartGame(){
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        M.setItemName(itemStack, "Start Game");
        M.setItemNBTName(itemStack, "startGame");
        return itemStack;
    }

    static ItemStack createControlLeft(){
        ItemStack itemStack = new ItemStack(Material.CARROT);
        M.setItemName(itemStack, "Left");
        M.setItemNBTName(itemStack, "controlLeft");
        return itemStack;
    }

    static ItemStack createControlRight(){
        ItemStack itemStack = new ItemStack(Material.CARROT);
        M.setItemName(itemStack, "Right");
        M.setItemNBTName(itemStack, "controlRight");
        return itemStack;
    }
}
