package noppe.minecraft.burnberry.resourcegame.resources;

import noppe.minecraft.burnberry.resourcegame.GameResource;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SoulResource extends GameResource {
    public SoulResource(int amount) {
        super(amount, new ItemStack(Material.HEART_OF_THE_SEA), "Soul");
    }
}
