package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import strategy.direct_shoot;
import strategy.scatter_shoot;

public class BulletProp extends AbstractProp {

    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed() throws InterruptedException {
        Runnable r = () -> {
            HeroAircraft.GetHeroAircraft().setStrategy(new scatter_shoot());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HeroAircraft.GetHeroAircraft().setStrategy(new direct_shoot());
        };
        // 启动线程
        new Thread(r).start();
        System.out.println("FireSupply Active!");

        vanish();
    }



}
