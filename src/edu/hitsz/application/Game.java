package edu.hitsz.application;

import edu.hitsz.LoopPlayer;
import edu.hitsz.ScoreTable;
import edu.hitsz.OncePlayer;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.factory.*;
import edu.hitsz.prop.*;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.Random;
/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProp> Props;


    /**
     * 屏幕中出现的敌机最大数量
     */
    private int enemyMaxNumber = 5;


    /**
     * 当前得分
     */
    private int score = 0;
    private int score_div=0;
    /**
     * 当前时刻
     */
    private int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;

    /**
     * 游戏结束标志
     */
    private boolean gameOverFlag = false;

    private boolean hasMusic;
    public Game(int mode,boolean hasMusic) throws IOException {
        heroAircraft = HeroAircraft.GetHeroAircraft();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        Props=new LinkedList<>();
        this.hasMusic=hasMusic;
        BufferedImage bg;
        if(mode==1){
             bg= ImageIO.read(new FileInputStream("src/images/bg.jpg"));
        }
        else if(mode==2){
             bg= ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
        }
        else {
             bg= ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
        }
        ImageManager.setBackgroundImage(bg);
        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }


    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {
        //MusicThread bgm=new MusicThread("src/videos/bgm.wav");
        //bgm.start();
        LoopPlayer bgm=new LoopPlayer("src/videos/bgm.wav");
        bgm.play();


        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生

                if (enemyAircrafts.size() < enemyMaxNumber) {
                    double r=Math.random();
                    if(r<=0.8){
                        enemyAircrafts.add(new MobEnemy_factory().createEnemy());}
                    else if(r<=0.9){
                        enemyAircrafts.add(new EliteEnemy_factory().createEnemy());}
                    else {
                        enemyAircrafts.add(new ElitePlusEnemy_factory().createEnemy());}
                }
                boolean flag=false;
                for(AbstractAircraft enemy:enemyAircrafts)
                {
                    if(enemy instanceof BossEnemy){
                        flag=true;

                    }
                }
                if(flag==false&score_div>=100){
                    enemyAircrafts.add(new BossEnemy_factory().createEnemy());
                    score_div=0;
                }
                // 飞机射出子弹
                shootAction();
            }


            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            try {
                crashCheckAction();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束

                OncePlayer game_over = new OncePlayer("src/videos/game_over.wav");
                game_over.start();

                System.out.println("Game Over!");
                gameOverFlag = true;
                executorService.shutdown();
                ScoreTable scoreTable = new ScoreTable();
                scoreTable.setScore(score);
                Main.cardPanel.add(scoreTable.getMainPanel());
                Main.cardLayout.last(Main.cardPanel);

            }
            //游戏结束停止音乐
            if(isGameOverFlag()){

                bgm.over();

                for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                    if(enemyAircraft instanceof BossEnemy){
                        ((BossEnemy) enemyAircraft).getBoss_music().over();
                    }
                }
            }
        };


        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }




    public void action_no_music() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生

                if (enemyAircrafts.size() < enemyMaxNumber) {
                    double r=Math.random();
                    if(r<=0.8){
                        enemyAircrafts.add(new MobEnemy_factory().createEnemy());}
                    else if(r<=0.9){
                        enemyAircrafts.add(new EliteEnemy_factory().createEnemy());}
                    else {
                        enemyAircrafts.add(new ElitePlusEnemy_factory().createEnemy());}
                }
                boolean flag=false;
                for(AbstractAircraft enemy:enemyAircrafts)
                {
                    if(enemy instanceof BossEnemy){
                        flag=true;

                    }
                }
                if(flag==false&score_div>=100){
                    enemyAircrafts.add(new BossEnemy_factory().createEnemy());
                    score_div=0;
                }
                // 飞机射出子弹
                shootAction();
            }


            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            try {
                crashCheckAction();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束

                System.out.println("Game Over!");
                gameOverFlag = true;
                executorService.shutdown();
                ScoreTable scoreTable=new ScoreTable();
                scoreTable.setScore(score);
                Main.cardPanel.add(scoreTable.getMainPanel());
                Main.cardLayout.last(Main.cardPanel);

            }
        };


        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        //循环修改代码，因为敌机不止一台
        for(AbstractAircraft enemy:enemyAircrafts){
            enemyBullets.addAll(enemy.shoot());
        }

        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() throws InterruptedException {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if(heroAircraft.crash(bullet)){
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值


                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给

                        if(enemyAircraft instanceof EliteEnemy || enemyAircraft instanceof ElitePlusEnemy)
                        {
                            double i= Math.random();
                            if(i<=0.25){
                                Props.add(new BloodProp_Factory().createProp(enemyAircraft.getLocationX(),enemyAircraft.getLocationY(),0,3));
                            } else if (i<0.5) {
                                Props.add(new BombProp_Factory().createProp(enemyAircraft.getLocationX(),enemyAircraft.getLocationY(),0,3));
                            }
                            else if(i<0.75){
                                 Props.add(new BulletProp_Factory().createProp(enemyAircraft.getLocationX(),enemyAircraft.getLocationY(),0,3));
                            }
                            else{
                                Props.add(new BulletPlusProp_Factory().createProp(enemyAircraft.getLocationX(),enemyAircraft.getLocationY(),0,3));
                            }
                        }
                        if(enemyAircraft instanceof BossEnemy){
                            ((BossEnemy) enemyAircraft).getBoss_music().over();
                            double j= Math.random();
                            Random random = new Random();
                            for(int i=0;i<random.nextInt(4);i++){
                                if(j<=0.25){
                                    Props.add(new BloodProp_Factory().createProp(enemyAircraft.getLocationX()+i*50,enemyAircraft.getLocationY()+i*50,0,3));
                                } else if (j<0.5) {
                                    Props.add(new BombProp_Factory().createProp(enemyAircraft.getLocationX()+i*50,enemyAircraft.getLocationY()+i*50,0,3));
                                }
                                else if(j<0.75){
                                    Props.add(new BulletProp_Factory().createProp(enemyAircraft.getLocationX()+i*50,enemyAircraft.getLocationY()+i*50,0,3));
                                }
                                else{
                                    Props.add(new BulletPlusProp_Factory().createProp(enemyAircraft.getLocationX()+i*50,enemyAircraft.getLocationY()+i*50,0,3));
                                }
                            }

                        }
                        score += 10;
                        score_div+=10;
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for (AbstractProp prop:Props){
            if(prop.notValid()){
                continue;
            }
            if (prop.crash(heroAircraft)){
                prop.BeUsed();
                if(hasMusic){
                    OncePlayer get_supply=new OncePlayer("src/videos/get_supply.wav");
                    get_supply.start();
                }
            }
            else{
                prop.forward();
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        Props.removeIf(AbstractFlyingObject::notValid);
    }



    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g,Props);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    public boolean isGameOverFlag() {
        return gameOverFlag;
    }
}
