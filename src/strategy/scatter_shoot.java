package strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class scatter_shoot implements Strategy{
    private int shootNum = 3;

    /**
     * 子弹伤害
     */
    private int power = 20;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;
    @Override
    public List<BaseBullet> execute(AbstractAircraft aircraft) {
            List<BaseBullet> res = new LinkedList<>();
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY() - direction*2;
            int speedX = 0;
            int speedY = aircraft.getSpeedY() - direction*5;
            BaseBullet bullet;
            for(int i=0; i<shootNum; i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX+2*i-2, speedY,power);
                res.add(bullet);

            }
            return res;
        }
}

