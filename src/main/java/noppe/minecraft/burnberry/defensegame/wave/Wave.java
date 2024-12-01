package noppe.minecraft.burnberry.defensegame.wave;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.defensegame.enemies.DefenseEnemy;

public abstract class Wave {
    public DefenseGame game;

    public int spawnDelay=1;
    public int spawnRate;
    public int stage=0;
    public int stageTimeLeft;

    public Wave(DefenseGame game){
        this.game = game;
        updateRates();
        updateStage();
    }

    public void onTick(){
        stageTimeLeft -= 1;
        if (stageTimeLeft <= 0){
            updateStage();
        }

        spawnDelay -= spawnRate;
        if (spawnDelay <= 0){
            spawnEnemy();
        }
    }

    public void updateStage(){
        stage += 1;
        _updateStage();
        updateRates();
    }

    public void end(){
        game.onWaveEnd();
    }

    public void spawnEnemy(){
        DefenseEnemy enemy = _spawnEnemy();
        enemy.setTarget(game.anchor);
        game.monsters.add(enemy);
        spawnDelay += enemy.miasma;
    }

    public abstract void updateRates();
    protected abstract void _updateStage();
    public abstract DefenseEnemy _spawnEnemy();
}
