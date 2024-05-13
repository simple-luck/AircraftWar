package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;


public class BloodProp extends AbstractProp {
    private int blood=100;
    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed() {
        HeroAircraft.GetHeroAircraft().add_blood(this.blood);
        vanish();
    }

    @Override
    public void forward() {
        super.forward();
    }
}
