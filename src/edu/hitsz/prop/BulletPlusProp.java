package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.AbstractFlyingObject;
import strategy.circle_shoot;

public class BulletPlusProp extends AbstractProp {
    public BulletPlusProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed(HeroAircraft hero) {
        System.out.println("FireSupply active!");
        hero.setStrategy(new circle_shoot());
        vanish();
    }
}
