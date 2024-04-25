package strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface Strategy {
    public abstract List<BaseBullet> execute(AbstractAircraft aircraft);
}
