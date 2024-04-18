package edu.hitsz.aircraft;
import edu.hitsz.bullet.BaseBullet;
import java.util.LinkedList;
import java.util.List;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.EnemyBullet;

public class ElitePlusEnemy extends AbstractAircraft{
    private int shootNum = 3;

    /**
     * 子弹伤害
     */
    private int power = 20;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;

    public ElitePlusEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() - direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() - direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if(i==0){
                bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX-2, speedY,power);
                res.add(bullet);
            }
            if(i==1){
                bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY,power);
                res.add(bullet);
            }
            if(i==2){
                bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX+2, speedY,power);
                res.add(bullet);
            }

        }
        return res;
    }
}
