package com.example.lroigefo11alumnes.provaboles2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    GameView game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new GameView(this);
        setContentView(game);
        game.onStart();
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
