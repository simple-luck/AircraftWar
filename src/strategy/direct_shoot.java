package strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class direct_shoot implements Strategy{

    private int shootNum=1;
    private int power=10;
    @Override
    public List<BaseBullet>  execute(AbstractAircraft aircraft) {
            List<BaseBullet> res = new LinkedList<>();
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY() + aircraft.direction*2;
            int speedX = 0;
            int speedY = aircraft.getSpeedY() + aircraft.direction*5;
            BaseBullet bullet;
            if(aircraft instanceof HeroAircraft){
                for(int i=0; i<shootNum; i++){
                    // 子弹发射位置相对飞机位置向前偏移
                    // 多个子弹横向分散
                    bullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, 3*power);
                    res.add(bullet);
                }
            }
            else{
                for(int i=0; i<shootNum; i++){
                    // 子弹发射位置相对飞机位置向前偏移
                    // 多个子弹横向分散
                    bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
                    res.add(bullet);
                }
            }
            return res;
    }
}
