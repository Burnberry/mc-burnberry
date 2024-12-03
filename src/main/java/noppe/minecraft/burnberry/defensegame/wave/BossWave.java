package noppe.minecraft.burnberry.defensegame.wave;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.defensegame.enemies.DefenseEnemy;
import noppe.minecraft.burnberry.defensegame.enemies.DefenseGiantGolem;
import noppe.minecraft.burnberry.defensegame.enemies.DefenseWeakZombie;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.location.Loc;
import org.bukkit.Sound;

public class BossWave extends Wave{
    int ticks=0;
    public BossWave(DefenseGame game) {
        super(game);
    }

    @Override
    public void updateRates() {
        spawnRate = 1;
    }

    @Override
    public void onTick() {
        super.onTick();
        stageTimeLeft = 100;
        if (ticks == 0){
            M.playWorldSound(Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, .2f);
            spawnGolem(12);
        } else if (ticks == 40){
            M.playWorldSound(Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, .3f);
        } else if (ticks == 75){
            M.playWorldSound(Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, .5f);
        } else if (ticks == 100){
            M.playWorldSound(Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, .6f);
        } else if (ticks == 115){
            M.playWorldSound(Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, .8f);
        } else if (ticks == 122){
            M.playWorldSound(Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, 1f);
        } else if (ticks == 128){
            M.playWorldSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1f);
            game.setWall(false);
            game.setSpawnPoints2();
        } else if (ticks%2400 == 0){
            spawnRate += 1;
        }
        ticks++;
    }

    @Override
    protected void _updateStage() {
        if (stage == 1){
            stageTimeLeft = 20*30*1000;
        }
        else {
            end();
        }
    }

    @Override
    public DefenseEnemy _spawnEnemy() {
        return new DefenseWeakZombie(game, game.getRandomSpawn());
    }

    public void spawnGolem(int zombies){
        spawnEnemy(new DefenseGiantGolem(game, Loc.wall.clone().add(4, 0, 0)));
        for (int i=0; i<zombies; i++){
            spawnEnemy(new DefenseWeakZombie(game, Loc.wall.clone().add(2, 0, 0)));
        }
        spawnDelay = 0;
    }
}
