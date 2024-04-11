package edu.hitsz.prop;

public class BulletProp_Factory implements PropFactory{
    @Override
    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY) {
        return new BulletProp(locationX,locationY,speedX,speedY);
    }
}
