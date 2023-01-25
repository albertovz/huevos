package com.example.huevos.models;

import java.util.Observable;
import java.util.Random;

public class Nube extends Observable implements Runnable {

    private Vector pos;
    private int distanciaX;
    private boolean status;

    public Nube () { status = true; }

    public void setPosicion (Vector pos) {
        this.pos = pos;
    }

    @Override
    public void run() {

        while (status) {
            setChanged();
            notifyObservers(pos);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            pos.setX(pos.getX() + distanciaX);

            if(pos.getX() >= 679) {
                distanciaX -= 20;
            }

            if(pos.getX() <= 10) {
                distanciaX += 20;
            }

        }

    }

    public void setStatus (boolean status) { this.status = status; }

}
