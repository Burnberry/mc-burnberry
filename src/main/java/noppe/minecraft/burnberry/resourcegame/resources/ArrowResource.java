package noppe.minecraft.burnberry.resourcegame.resources;

import noppe.minecraft.burnberry.resourcegame.GameResource;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ArrowResource extends GameResource {
    public ArrowResource() {
        super(0, new ItemStack(Material.ARROW), "Arrows");
    }
}
