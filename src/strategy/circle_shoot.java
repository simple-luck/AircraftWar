package strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class circle_shoot implements Strategy{
    private int shootNum=20;
    private int power=20;
    @Override
    public List<BaseBullet> execute(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY();
        int BULLET_SPEED = 14;
        BaseBullet bullet;
        double angleIncrement = 2 * Math.PI / shootNum;
        if(aircraft instanceof HeroAircraft){
            for (int i = 0; i < shootNum; i++) {
                // 子弹发射位置相对飞机位置向前偏移
                double angle = i * angleIncrement;
                int bullet_speedX = (int) (BULLET_SPEED * Math.cos(angle));
                int bullet_speedY = (int) (BULLET_SPEED * Math.sin(angle));
                bullet = new HeroBullet(x,y, bullet_speedX, bullet_speedY, 3*power);
                res.add(bullet);
            }
        }
        else {
            for (int i = 0; i < shootNum; i++) {
                // 子弹发射位置相对飞机位置向前偏移
                double angle = i * angleIncrement;
                int bullet_speedX = (int) (BULLET_SPEED * Math.cos(angle));
                int bullet_speedY = (int) (BULLET_SPEED * Math.sin(angle));
                bullet = new EnemyBullet(x,y, bullet_speedX, bullet_speedY, power);
                res.add(bullet);
            }
        }
        return res;

    }
}
