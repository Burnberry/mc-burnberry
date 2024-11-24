package noppe.minecraft.burnberry.defensegame.enemies;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Mob;

public class DefenseMiniGhast extends DefenseEnemy{
    public DefenseMiniGhast(DefenseGame game, Location location) {
        super(game, (Mob) M.spawnEntity(game, location, Ghast.class));
        getMob().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
        getMob().getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.1);
        getMob().setSilent(true);
    }
}
