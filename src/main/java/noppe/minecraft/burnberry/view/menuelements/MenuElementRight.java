package noppe.minecraft.burnberry.view.menuelements;

import noppe.minecraft.burnberry.item.Menu;
import noppe.minecraft.burnberry.view.MenuElement;
import noppe.minecraft.burnberry.view.View;
import org.bukkit.inventory.ItemStack;

public class MenuElementRight extends MenuElement {
    public MenuElementRight() {
        super(Menu.controlRight);
    }

    @Override
    public void onClick(View view) {
        view.page += 1;
        view.reload();
    }
}
