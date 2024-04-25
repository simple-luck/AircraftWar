package strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public class null_shoot implements Strategy{
    @Override
    public List<BaseBullet> execute(AbstractAircraft aircraft) {
        return new LinkedList<>();
    }
}
