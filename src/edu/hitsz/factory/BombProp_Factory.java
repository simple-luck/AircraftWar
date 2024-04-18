package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.PropFactory;

public class BombProp_Factory implements PropFactory {
    @Override
    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY) {
        return new BombProp(locationX,locationY,speedX,speedY);
    }
}
