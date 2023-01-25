package com.example.huevos.models;

import java.util.Observable;
import java.util.Random;

public class Huevos extends Observable implements Runnable {

    private Vector pos;
    private boolean status;
    private Random random;
    private int vel = 0;

    public Huevos(int vel) {
        status = true;
        this.vel += vel;
    }

    public void setPosicion (Vector pos) {
        this.pos = pos;
        random = new Random(System.currentTimeMillis());
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    @Override
    public void run() {

        while (status) {
            pos.setY(pos.getY() + random.nextInt(10 + vel));
            setChanged();
            notifyObservers(pos);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void setStatus (boolean status) { this.status = status; }

    public boolean getStatus () { return status; }

}
