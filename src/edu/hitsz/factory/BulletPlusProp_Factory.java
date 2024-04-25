package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BulletPlusProp;
import edu.hitsz.prop.PropFactory;

public class BulletPlusProp_Factory implements PropFactory {
    @Override
    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY) {
        return new BulletPlusProp(locationX, locationY, speedX, speedY);
    }
}
