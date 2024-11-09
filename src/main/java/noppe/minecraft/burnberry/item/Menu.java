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

    public static ItemStack controlResources = createControlResources();
    public static ItemStack controlUpgrades = createControlUpgrades();
    public static ItemStack controlMines = createControlMines();

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

    static ItemStack createControlResources(){
        ItemStack itemStack = new ItemStack(Material.BARREL);
        M.setItemName(itemStack, "Resources");
        M.setItemNBTName(itemStack, "controlResources");
        return itemStack;
    }

    static ItemStack createControlUpgrades(){
        ItemStack itemStack = new ItemStack(Material.EMERALD);
        M.setItemName(itemStack, "Upgrades");
        M.setItemNBTName(itemStack, "controlUpgrades");
        return itemStack;
    }

    static ItemStack createControlMines(){
        ItemStack itemStack = new ItemStack(Material.STONE_PICKAXE);
        M.setItemName(itemStack, "Mines");
        M.setItemNBTName(itemStack, "controlMines");
        return itemStack;
    }
}
