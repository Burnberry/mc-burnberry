package noppe.minecraft.burnberry.resourcegame.resources;

import noppe.minecraft.burnberry.resourcegame.GameResource;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StoneResource extends GameResource {
    public StoneResource() {
        super(0, new ItemStack(Material.COBBLESTONE), "Stone");
    }
}
