package com.decodehelloworld.naarirakshak;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Siren extends AppCompatActivity {
    MediaPlayer player;
    public void play(View view){
        player.start();
    }
    public void pause(View view){
        player.pause();
    }
    public void stop(View view){
        player.stop();
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siren);
        player = MediaPlayer.create(this,R.raw.music);
    }
}
