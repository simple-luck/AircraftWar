package edu.hitsz.dao;

import java.util.List;

public interface DAO {
    public  abstract  List<PlayerData> getAll();
    public abstract PlayerData getByname(String name);
    public  abstract void add(PlayerData data);
    public  abstract void delete(String name);
}
