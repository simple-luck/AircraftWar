package edu.hitsz.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Daoiml implements DAO {

    private  File file ;

    public File getFile() {
        return file;
    }

    public void setFile(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public List<PlayerData> getAll() {
        return  Data_file.read(file);

    }

    @Override
    public PlayerData getByname(String name) {
        return null;
    }

    @Override
    public void add(PlayerData data) {
        Data_file.spannedFile(file);
        Data_file.write(file,data);
    }

    @Override
    public void delete(String name) {
        Data_file.removeObjectByName(file,name);
    }
}