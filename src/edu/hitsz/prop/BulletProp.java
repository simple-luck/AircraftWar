package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class BulletProp extends AbstractPop{

    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed(HeroAircraft hero) {
        System.out.println("FireSupply active!");
        vanish();
    }

    @Override
    public void forward() {
        super.forward();
    }
}
