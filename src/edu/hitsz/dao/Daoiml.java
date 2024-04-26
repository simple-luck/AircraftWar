package edu.hitsz.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Daoiml implements DAO{
    private String filePath;
    //String filePath = "player_data.ser";

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public  List<PlayerData> getAll() {
        List<PlayerData> allPlayerData = Data_file.getAllPlayerData(filePath);
        if (!allPlayerData.isEmpty()) {
            System.out.println("所有记录:");
            for (PlayerData playerData : allPlayerData) {
                System.out.println(playerData);
            }
        } else {
            System.out.println("找不到任何记录");
        }
        return allPlayerData;
    }


    @Override
    public PlayerData getByname(String name) {
        return Data_file.getPlayerDataByUsername(filePath,name);
    }

    @Override
    public  void add(PlayerData data) {
        Data_file.appendPlayerDataToFile(filePath,data);
    }

    @Override
    public  void delete(String name) {
        Data_file.removePlayerDataByUsername(filePath,name);
    }


}
