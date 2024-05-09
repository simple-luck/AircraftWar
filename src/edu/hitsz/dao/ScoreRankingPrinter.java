package edu.hitsz.dao;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreRankingPrinter{


    // 从文件中读取 PlayerData 对象，并生成得分排行榜
    public static List<PlayerData> generateScoreRanking(Daoiml dao) {
        List<PlayerData> playerDataList =dao.getAll();
        Collections.sort(playerDataList);
        return playerDataList;
    }

    // 打印得分排行榜
    public static void printScoreRanking(List<PlayerData> scoreRanking) {
        System.out.println("*****************************");
        System.out.println("得分排行榜:");
        System.out.println("*****************************");
        for (int i = 0; i < scoreRanking.size(); i++) {
            PlayerData playerData = scoreRanking.get(i);
            System.out.println("第"+(i + 1) +"名"+" " + playerData.getUsername() + "," + playerData.getScore()+","+playerData.getDate());
        }
    }


}
