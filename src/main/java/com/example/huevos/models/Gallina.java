package com.example.huevos.models;

import java.util.Observable;
import java.util.Random;

public class Gallina extends Observable implements Runnable {

    private Vector pos;
    private boolean status;

    public Gallina () { status = true; }

    public void setPosicion (Vector pos) {
        this.pos = pos;
    }

    @Override
    public void run() {

        while (status) {
            pos.setX(pos.getX() + 10);
            setChanged();
            notifyObservers(pos);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void setStatus (boolean status) { this.status = status; }

}
