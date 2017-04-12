package com.example.lroigefo11alumnes.provaboles2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class GameActivity extends AppCompatActivity {
    GameView game;
    private int touchX;
    private int touchY;
    private int gameover = -1;
    private final float SCROLL_THRESHOLD = 10;
    private boolean isOnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new GameView(this);
        setContentView(game);
        game.onStart();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                //El casting de float a int automaticament arrodoneix cap avall
                touchX = (int)ev.getX();
                touchY = (int)ev.getY();
                isOnClick = true;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (isOnClick) {
                    //El log envia un missatge (en aquest cas "onClick ") amb una etiqueta
                    //Log.i(LOG_TAG, "onClick ");
                    //TODO onClick code
                    gameover = game.hitBall(touchX, touchY);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isOnClick && (Math.abs(touchX - ev.getX()) > SCROLL_THRESHOLD || Math.abs(touchY - ev.getY()) > SCROLL_THRESHOLD)) {
                    //Log.i(LOG_TAG, "movement detected");
                    isOnClick = false;
                }
                break;
            default:
                break;
        }
        if (gameover == 0){
            //fem les crides als fragments que toqui
            game.pintafons();
        } else if (gameover < 0){
            //same
            game.canviafons();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.startThread();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.stopThread();
    }

    //Aqui anira el metode onClick
}
