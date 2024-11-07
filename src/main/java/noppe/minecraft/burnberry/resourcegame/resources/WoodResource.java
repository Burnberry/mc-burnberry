package noppe.minecraft.burnberry.resourcegame.resources;

import noppe.minecraft.burnberry.resourcegame.GameResource;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WoodResource extends GameResource {
    public WoodResource() {
        super(0, new ItemStack(Material.OAK_LOG), "Wood");
    }
}
