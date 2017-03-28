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
    private int x, y, velX, velY, width, height, rad;
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
        rad = 40;
        velX = 10;
        velY = 8;
    }

    public void painting(Canvas c){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        c.drawColor(Color.BLACK);
        c.drawCircle(x, y, rad, paint);
        x += velX;
        y += velY;
        if ((x + rad) >= width || (x - rad) <= 0){
            velX = -velX;
        }
        if ((y + rad) >= height || (y - rad) <= 0){
            velY = -velY;
        }
    }

    public boolean hitBall(int touchX, int touchY){
        if (abs(touchX - x) < rad){
            return true;
        } else {
            return false;
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
}

