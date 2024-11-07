package noppe.minecraft.burnberry.resourcegame.upgrades;

import java.util.ArrayList;
import java.util.List;

public class Upgrades {
    public List<Upgrade> upgrades;
    public int pickaxePower = 1;

    public Upgrades(){
        upgrades = new ArrayList<>();
        upgrades.add(new upgradePickaxe());
    }
}
