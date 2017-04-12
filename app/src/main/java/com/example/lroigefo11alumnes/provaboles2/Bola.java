package com.example.lroigefo11alumnes.provaboles2;

/**
 * Created by Quanta on 10/4/2017.
 */

public class Bola {
    private int x, y, velX, velY;
    private int color;

    public Bola(int x, int y, int velX, int velY, int color) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public int getColor() {
        return color;
    }

    public void ferPas() {
        x += velX;
        y += velY;
    }

    public void canviVelY() {
        velY = -velY;
    }

    public void canviVelX() {
        velX = -velX;
    }
}
