package edu.hitsz.dao;

import java.io.Serializable;
import java.util.Date;

public class PlayerData implements Serializable, Comparable<PlayerData> {
    private static final long serialVersionUID = 1L;

    private String username;
    private int score;
    private String date;

    public PlayerData(String username, int score, String date) {
        this.username = username;
        this.score = score;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(PlayerData other) {
        // 降序排列
        return Integer.compare(other.score, this.score);
    }
}
