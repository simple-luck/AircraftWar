package edu.hitsz.prop;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.aircraft.AbstractAircraft;


public abstract class AbstractPop extends AbstractFlyingObject {
    public AbstractPop(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public boolean crash(AbstractFlyingObject flyingObject) {
        // 缩放因子，用于控制 y轴方向区域范围
        int factor = this instanceof AbstractPop ? 2 : 1; //我方
        int fFactor = flyingObject instanceof HeroAircraft? 2 : 1;//对方

        //对方坐标、宽度、高度
        int x = flyingObject.getLocationX();
        int y = flyingObject.getLocationY();
        int fWidth = flyingObject.getWidth();
        int fHeight = flyingObject.getHeight();

        return x + (fWidth+this.getWidth())/2 > locationX
                && x - (fWidth+this.getWidth())/2 < locationX
                && y + ( fHeight/fFactor+this.getHeight()/factor )/2 > locationY
                && y - ( fHeight/fFactor+this.getHeight()/factor )/2 < locationY;
    }

    public abstract void BeUsed(HeroAircraft hero);

    public void forward(){
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT ) {
            this.speedY=0;
        }
    }
}

