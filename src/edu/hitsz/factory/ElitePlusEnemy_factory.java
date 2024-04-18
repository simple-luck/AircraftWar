package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class ElitePlusEnemy_factory implements EnemyFactory {
    @Override
    public AbstractAircraft createEnemy() {
        return new ElitePlusEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ElitePlus_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                1,
                3,
                60
        );
    }
}
