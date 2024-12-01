package noppe.minecraft.burnberry.defensegame.enemies;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;

public class DefenseZombie extends DefenseEnemy {
    public DefenseZombie(DefenseGame game, Location location) {
        super(game, (Monster) M.spawnEntity(game, location, Zombie.class), 1);
        getMob().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
    }
}
