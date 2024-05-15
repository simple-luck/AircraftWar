package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemy_factory implements EnemyFactory {



    private boolean hasMusic;

    public void setHasMusic(boolean hasMusic) {
        this.hasMusic = hasMusic;
    }

    @Override
    public AbstractEnemy createEnemy(int hp_add,int speed_add) {
        return new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.Boss_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                1+speed_add,
                0,
                240+BossEnemy.count*10,
                hasMusic
        );
    }
}
