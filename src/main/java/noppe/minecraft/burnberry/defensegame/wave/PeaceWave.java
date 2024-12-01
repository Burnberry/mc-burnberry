package noppe.minecraft.burnberry.defensegame.wave;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.defensegame.enemies.DefenseEnemy;
import noppe.minecraft.burnberry.helpers.M;

public class PeaceWave extends Wave{
    public PeaceWave(DefenseGame game) {
        super(game);
    }

    @Override
    public void updateRates() {
        spawnRate = 0;
    }

    @Override
    protected void _updateStage() {
        if (stage == 1){
            stageTimeLeft = 20*30;
            M.print("Peace time " + stage);
        }
        else {
            end();
        }
    }

    @Override
    public DefenseEnemy _spawnEnemy() {
        return null;
    }
}
