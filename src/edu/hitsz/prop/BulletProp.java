package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import strategy.scatter_shoot;

public class BulletProp extends AbstractProp {

    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed(HeroAircraft hero) {
        System.out.println("FireSupply active!");
        hero.setStrategy(new scatter_shoot());
        vanish();
    }


}
