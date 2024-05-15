package edu.hitsz.application;

import edu.hitsz.ScoreTable;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.factory.BossEnemy_factory;
import edu.hitsz.factory.EliteEnemy_factory;
import edu.hitsz.factory.ElitePlusEnemy_factory;
import edu.hitsz.factory.MobEnemy_factory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class mediumGame extends Game{
    public mediumGame(int mode, boolean hasMusic) throws IOException {
        super(mode, hasMusic);
    }

    @Override
    public void ProductEnemy() {
        if (enemyAircrafts.size() < enemyMaxNumber+(int) (time*0.001)) {
            double r=Math.random();
            if(r<=1-(time*0.00001)*(time*0.00001)){
                enemyAircrafts.add(new MobEnemy_factory().createEnemy((int) (time*0.00015),(int)(time*0.000015)));}
            else if(r<=1-0.5*(time*0.00001)*(time*0.00001)){
                enemyAircrafts.add(new EliteEnemy_factory().createEnemy((int) (time*0.00015),(int)(time*0.000015)));}
            else {
                enemyAircrafts.add(new ElitePlusEnemy_factory().createEnemy((int) (time*0.00015),(int)(time*0.000015)));}
        }

        boolean flag = false;
        for (AbstractAircraft enemy : enemyAircrafts) {
            if (enemy instanceof BossEnemy) {
                flag = true;
            }
        }

        if (!flag & score_div >= 200) {

            BossEnemy_factory boss_factory = new BossEnemy_factory();
            boss_factory.setHasMusic(hasMusic);
            enemyAircrafts.add(boss_factory.createEnemy(0,(int)(time*0.00001)));
            score_div = 0;
        }

    }
    @Override
    public void setBg() throws IOException {
        BufferedImage bg= ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
        ImageManager.setBackgroundImage(bg);
    }

    @Override
    public void setTable() {
        ScoreTable scoreTable = new ScoreTable(2);
        scoreTable.setScore(score);
        Main.cardPanel.add(scoreTable.getMainPanel());
        Main.cardLayout.last(Main.cardPanel);
    }

}
