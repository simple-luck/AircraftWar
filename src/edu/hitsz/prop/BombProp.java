package edu.hitsz.prop;

import edu.hitsz.OncePlayer;

public class BombProp extends AbstractProp {

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed() throws InterruptedException {
        System.out.println("BombSupply active");
        OncePlayer bomb_music=new OncePlayer("src/videos/bomb_explosion.wav");
        bomb_music.start();
        vanish();
    }

    @Override
    public void forward() {
        super.forward();
    }
}
