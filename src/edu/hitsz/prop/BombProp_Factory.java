package edu.hitsz.prop;

public class BombProp_Factory implements PropFactory{
    @Override
    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY) {
        return new BombProp(locationX,locationY,speedX,speedY);
    }
}
