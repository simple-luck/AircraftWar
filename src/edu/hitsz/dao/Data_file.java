package edu.hitsz.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data_file {
    public static void writePlayerDataToFile(String filePath, PlayerData playerData) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(playerData);
            //System.out.println("PlayerData 对象已成功写入到文件: " + filePath);
        } catch (IOException e) {
            System.err.println("写入文件时出错: " + e.getMessage());
        }
    }
    public static void appendPlayerDataToFile(String filePath, PlayerData playerData) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            outputStream.writeObject(playerData);
            System.out.println("PlayerData 对象已成功追加到文件: " + filePath);
        } catch (IOException e) {
            System.err.println("写入文件时出错: " + e.getMessage());
        }
    }

    public static void removePlayerDataByUsername(String filePath, String usernameToRemove) {
        List<PlayerData> playerDataList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    PlayerData playerData = (PlayerData) inputStream.readObject();
                    if (!playerData.getUsername().equals(usernameToRemove)) {
                        playerDataList.add(playerData);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (EOFException e) {
            // 到达文件末尾
            System.out.println("已读取所有数据");
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        }

        // 将剩余的数据写入到文件中
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (PlayerData playerData : playerDataList) {
                outputStream.writeObject(playerData);
            }
            System.out.println("已删除玩家名为 " + usernameToRemove + " 的记录");
        } catch (IOException e) {
            System.err.println("写入文件时出错: " + e.getMessage());
        }
    }


    // 根据用户名从文件中获取对应的记录
    public static PlayerData getPlayerDataByUsername(String filePath, String usernameToRetrieve) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    PlayerData playerData = (PlayerData) inputStream.readObject();
                    if (playerData.getUsername().equals(usernameToRetrieve)) {
                        return playerData;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (EOFException e) {
            // 到达文件末尾
            System.out.println("已读取所有数据");
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        }
        return null;
    }

    // 从文件中获取所有记录
    public static List<PlayerData> getAllPlayerData(String filePath) {
        List<PlayerData> playerDataList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    PlayerData playerData = (PlayerData) inputStream.readObject();
                    playerDataList.add(playerData);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (EOFException e) {
            // 到达文件末尾
            System.out.println("已读取所有数据");
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        }
        return playerDataList;
    }
}
