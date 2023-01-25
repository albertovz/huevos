package com.example.huevos.models;

public class Vector {
    private int x;
    private int y;
    private int id;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector(int id, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
}
