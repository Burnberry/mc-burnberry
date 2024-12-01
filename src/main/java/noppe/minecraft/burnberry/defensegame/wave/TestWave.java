package noppe.minecraft.burnberry.defensegame.wave;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.defensegame.enemies.DefenseEnemy;
import noppe.minecraft.burnberry.defensegame.enemies.DefenseWeakZombie;
import noppe.minecraft.burnberry.helpers.M;

public class TestWave extends Wave {
    public TestWave(DefenseGame game) {
        super(game);
    }

    @Override
    public void updateRates() {
        spawnRate = 1;
    }

    @Override
    protected void _updateStage() {
        if (stage == 1){
            stageTimeLeft = 20*30;
            M.print("Zombies incoming " + stage);
        }
        else {
            end();
        }
    }

    @Override
    public DefenseEnemy _spawnEnemy() {
        return new DefenseWeakZombie(game, game.getRandomSpawn());
    }
}
