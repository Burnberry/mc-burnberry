package noppe.minecraft.burnberry.defensegame.enemies;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.entities.enemies.CustomEnemy;
import noppe.minecraft.burnberry.event.CustomEventListener;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Monster;

public class DefenseEnemy extends CustomEnemy {
    public DefenseGame game;
    public int souls = 1;

    public DefenseEnemy(DefenseGame game, Monster monster) {
        super(game, monster);
        this.game = game;
    }

    public Monster getMonster(){
        return (Monster) entity;
    }
}
