package edu.hitsz.application;

import edu.hitsz.LoopPlayer;
import edu.hitsz.OncePlayer;
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

public class easyGame extends Game{
    public easyGame( boolean hasMusic) throws IOException {
        super( hasMusic);
    }

    @Override
    public void setBg() throws IOException {
        BufferedImage bg= ImageIO.read(new FileInputStream("src/images/bg.jpg"));
        ImageManager.setBackgroundImage(bg);
    }
    public void setTable(){
        ScoreTable scoreTable = new ScoreTable(1);
        scoreTable.setScore(score);
        Main.cardPanel.add(scoreTable.getMainPanel());
        Main.cardLayout.last(Main.cardPanel);
    }

    @Override
    public void ProductEnemy() {
        if (enemyAircrafts.size() < enemyMaxNumber) {
            double r=Math.random();
            if(r<=0.8){
                enemyAircrafts.add(new MobEnemy_factory().createEnemy(0,0));}
            else if(r<=0.9){
                enemyAircrafts.add(new EliteEnemy_factory().createEnemy(0,0));}
            else {
                enemyAircrafts.add(new ElitePlusEnemy_factory().createEnemy(0,0));}
        }
    }

}
