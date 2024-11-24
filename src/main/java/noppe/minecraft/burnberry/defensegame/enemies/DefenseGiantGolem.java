package noppe.minecraft.burnberry.defensegame.enemies;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

public class DefenseGiantGolem extends DefenseEnemy{
    public DefenseGiantGolem(DefenseGame game, Location location) {
        super(game, (Mob) M.spawnEntity(game, location, IronGolem.class));
        getMob().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
        getMob().getAttribute(Attribute.GENERIC_SCALE).setBaseValue(4);
    }
}
