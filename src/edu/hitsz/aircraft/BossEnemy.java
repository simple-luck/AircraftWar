package edu.hitsz.aircraft;

import edu.hitsz.LoopPlayer;
import edu.hitsz.OncePlayer;
import strategy.circle_shoot;

public class BossEnemy extends AbstractEnemy {


    /**
     * 子弹射击方向 (向上发射：-1，向下发射：1)
     */
    public static int count=0;
    private int maxHp=360+10*count;
    private LoopPlayer boss_music=new LoopPlayer("src/videos/bgm_boss.wav");


    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp,boolean hasMusic) {
        super(locationX, locationY, speedX, speedY, hp);
        this.setStrategy(new circle_shoot());
        this.direction=1;
        if(hasMusic){
            boss_music.play();
        }
    }


    public LoopPlayer getBoss_music() {
        return boss_music;

    }

    @Override
    public void update() {
    }
}