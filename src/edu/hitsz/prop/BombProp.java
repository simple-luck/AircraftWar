package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class BombProp extends AbstractPop{

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed(HeroAircraft hero) {
        System.out.println("BombSupply active");
    }

    @Override
    public void forward() {
        super.forward();
    }
}
