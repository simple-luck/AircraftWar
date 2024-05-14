package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.AbstractEnemy;

public interface EnemyFactory {
    public abstract AbstractEnemy createEnemy(int mode);
}
