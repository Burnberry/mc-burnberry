package noppe.minecraft.burnberry.resourcegame.resources;

import noppe.minecraft.burnberry.resourcegame.GameResource;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class IronResource extends GameResource {
    public IronResource(int amount) {
        super(amount, new ItemStack(Material.RAW_IRON), "Iron");
    }
}
