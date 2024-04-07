package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.aircraft.HeroAircraft;


public class BloodProp extends AbstractPop{
    protected int blood=30;
    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed(HeroAircraft hero) {
        hero.add_blood(this.blood);
        vanish();
    }

    @Override
    public void forward() {
        super.forward();
    }
}
