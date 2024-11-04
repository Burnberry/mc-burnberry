package noppe.minecraft.burnberry.view.menuelements;

import noppe.minecraft.burnberry.item.Menu;
import noppe.minecraft.burnberry.view.MenuElement;
import noppe.minecraft.burnberry.view.View;
import org.bukkit.inventory.ItemStack;

public class DecorationMenuElement extends MenuElement {
    public DecorationMenuElement() {
        super(Menu.black);
    }

    public DecorationMenuElement(ItemStack itemStack) {
        super(itemStack);
    }

    @Override
    public void onClick(View view) {
    }
}