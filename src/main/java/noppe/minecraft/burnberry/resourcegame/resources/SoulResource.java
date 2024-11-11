package noppe.minecraft.burnberry.resourcegame.resources;

import noppe.minecraft.burnberry.resourcegame.GameResource;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SoulResource extends GameResource {
    public SoulResource() {
        super(0, new ItemStack(Material.HEART_OF_THE_SEA), "Soul");
    }
}
