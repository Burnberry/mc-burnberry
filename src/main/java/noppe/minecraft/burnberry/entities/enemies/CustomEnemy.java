package noppe.minecraft.burnberry.entities.enemies;

import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.event.CustomEventListener;
import org.bukkit.EntityEffect;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomEnemy extends CustomEntity {
    public CustomEnemy(CustomEventListener origin, Enemy enemy){
        super(origin, enemy);
    }

    public Enemy getEnemy(){
        return (Enemy) entity;
    }

    public Boolean isEnemy(){
        return true;
    }
}
