package com.example.lroigefo11alumnes.provaboles2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static java.lang.Math.abs;

/**
 * Created by lroigefo11.alumnes on 28/03/17.
 */

public class GameView extends SurfaceView implements Runnable{
    SurfaceHolder holder;
    Canvas c;
    private Bola bola;
    private int x, y, velX, velY, width, height, rad, color;
    private boolean run;
    private Thread thread;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        c = new Canvas();
        width = 1080;
        height = 1920;
        holder.setFixedSize(width, height);
        x = 200;
        y = 200;
        rad = 60;
        velX = 20;
        velY = 16;
        color = Color.RED;
        bola = new Bola(x, y, velX, velY, color);
    }

    public void painting(Canvas c){
        Paint paint = new Paint();
        paint.setColor(bola.getColor());
        c.drawColor(Color.BLACK);
        c.drawCircle(bola.getX(), bola.getY(), rad, paint);
        bola.ferPas();
        if ((bola.getX() + rad) >= width || (bola.getX() - rad) <= 0){
            bola.canviVelX();
        }
        if ((bola.getY() + rad) >= height || (bola.getY() - rad) <= 0){
            bola.canviVelY();
        }
    }

    public int hitBall(int touchX, int touchY){
        boolean hit = false;//revisar si cal, crec que no
        int i = 0;
        if (abs(touchX - bola.getX()) < rad && abs(touchY - bola.getY()) < rad){
            return i;
        } else {
            return -1;
        }
    }

    @Override
    public void run() {
        while(run){
            if(holder.getSurface().isValid()) {
                c = holder.lockCanvas();
                painting(c);
                holder.unlockCanvasAndPost(c);
            }
        }
    }

    public void startThread(){
        thread = new Thread(this);
        setRun(true);
        thread.start();
    }

    public void stopThread(){
        setRun(false);
        boolean stop = true;
        while (stop) {
            try {
                thread.join();
                stop = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void setRun(boolean r){
        this.run = r;
    }

    public void onStart(){
        setRun(true);
    }

    public void pintafons() {
        c = holder.lockCanvas();
        c.drawColor(Color.BLACK);
        holder.unlockCanvasAndPost(c);
    }
}

