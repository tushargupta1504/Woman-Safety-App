package com.decodehelloworld.naarirakshak;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Combined extends AppCompatActivity {

    ImageButton b1,b2,b4,b5;
    Button b3;
    MediaPlayer player;
    Ringtone r;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public void siren(){
        if(!player.isPlaying()){
            player.start();
            //showNotification("Player State Plying");
        }
        else if(player.isPlaying()){
            player.pause();
            //showNotification("Player State Pause");
        }
    }




    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined);
        player = MediaPlayer.create(this,R.raw.music);
        Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), ringtone);



        b1 = findViewById(R.id.button5);
        b2 = findViewById(R.id.button6);
        b3 = findViewById(R.id.button7);
        b4 = findViewById(R.id.button8);
        b5 = findViewById(R.id.imageButton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Combined.this,RegisterNumberActivity.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siren();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent a = new Intent(Combined.this, SignIn.class);
                startActivity(a);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Combined.this,Helpline.class));
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               r.play();

            }
        });
    }
}
