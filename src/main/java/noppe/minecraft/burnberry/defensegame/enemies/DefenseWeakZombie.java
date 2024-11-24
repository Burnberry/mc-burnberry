package noppe.minecraft.burnberry.defensegame.enemies;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;

public class DefenseWeakZombie extends DefenseEnemy{
    public DefenseWeakZombie(DefenseGame game, Location location) {
        super(game, (Monster) M.spawnEntity(game, location, Zombie.class));
        getMob().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
        getMob().getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.7);
    }
}
