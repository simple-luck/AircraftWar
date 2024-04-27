package edu.hitsz.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data_file {
    /**
     * 初始化一次即可,为了能够提前存在4个字节的序列化头部信息
     *
     * @param file
     */
    public static void spannedFile(File file) {
        if (file.exists()) {
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(File file, PlayerData playerData) {
        try (FileOutputStream fos = new FileOutputStream(file, true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            fos.getChannel().truncate(fos.getChannel().position() - 4);
            oos.writeObject(playerData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<PlayerData> read(File file) {
        List<PlayerData> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                try {
                    Object o = ois.readObject();
                    if (o instanceof PlayerData) {
                        list.add((PlayerData) o);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void removeObjectByName(File file, String nameToRemove) {
        // 读取所有对象
        List<PlayerData> list = read(file);

        // 从列表中删除名字匹配的对象
        list.removeIf(playerData -> playerData.getUsername().equals(nameToRemove));

        // 清空原文件
        if (!file.delete()) {
            System.err.println("无法删除原文件");
            return;
        }

        // 重新创建文件
        spannedFile(file);

        // 将剩余的对象写回文件
        for (PlayerData playerData : list) {
            write(file, playerData);
        }
    }

}
