package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import strategy.circle_shoot;
import strategy.direct_shoot;

public class BulletPlusProp extends AbstractProp {
    public BulletPlusProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed() throws InterruptedException {
        Runnable r = () -> {
            HeroAircraft.GetHeroAircraft().setStrategy(new circle_shoot());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HeroAircraft.GetHeroAircraft().setStrategy(new direct_shoot());
        };
        // 启动线程
        new Thread(r, "线程2").start();
        System.out.println("FireSupply active!");
        vanish();
    }
}
