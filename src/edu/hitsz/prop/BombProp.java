package edu.hitsz.prop;

import edu.hitsz.OncePlayer;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.List;

public class BombProp extends AbstractProp {
    private List<AbstractEnemy> enemyList;
    private List<BaseBullet> enemyBulletList;
    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void BeUsed() {
        System.out.println("BombSupply active");
        notifyObservers();
        vanish();
    }

    @Override
    public void forward() {
        super.forward();
    }

    public void addEnemy(List<AbstractEnemy> enemy){
        enemyList=enemy;
    }
    public void addBullets(List<BaseBullet> bullets){
        enemyBulletList=bullets;
    }

    public void notifyObservers(){
        for(AbstractEnemy enemy:enemyList){
            enemy.update();
        }
        for(BaseBullet bullet:enemyBulletList){
            bullet.update();
        }
    }
}
