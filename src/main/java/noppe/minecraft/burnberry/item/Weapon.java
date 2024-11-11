package noppe.minecraft.burnberry.item;

import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Weapon {
    public static ItemStack bow = Weapon.createBow();

    private static ItemStack createBow() {
        ItemStack itemStack = new ItemStack(Material.BOW);
        itemStack.addEnchantment(Enchantment.INFINITY, 1);
        return itemStack;
    }
}
