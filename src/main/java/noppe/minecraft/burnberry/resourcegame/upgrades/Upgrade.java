package noppe.minecraft.burnberry.resourcegame.upgrades;

import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.util.Spendable;

public abstract class Upgrade extends Spendable {
    public int level=0;

    public void buy(ResourceGame game){
        super.buy(game);
        level += 1;
        updateCosts();
    }
}
