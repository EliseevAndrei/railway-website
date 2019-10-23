package com.eliseev.app.models;

public class Train {

    private long id;
    private String name;
    private int countKupe;
    private int countPlaz;
    private int countGen;

    public Train() {
    }

    public Train(long id, String name, int countKupe, int countPlaz, int countGen) {
        this.id = id;
        this.name = name;
        this.countKupe = countKupe;
        this.countPlaz = countPlaz;
        this.countGen = countGen;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountKupe(int countKupe) {
        this.countKupe = countKupe;
    }

    public void setCountPlaz(int countPlaz) {
        this.countPlaz = countPlaz;
    }

    public void setCountGen(int countGen) {
        this.countGen = countGen;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountKupe() {
        return countKupe;
    }

    public int getCountPlaz() {
        return countPlaz;
    }

    public int getCountGen() {
        return countGen;
    }

    @Override
    public String toString() {
        return "Train{" +
                "name='" + name + '\'' +
                ", countKupe=" + countKupe +
                ", countPlaz=" + countPlaz +
                ", countGen=" + countGen +
                '}';
    }
}