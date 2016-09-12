package com.example.kevin.a2dgame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    public static MediaPlayer backgroundMusic;
    public static boolean musicPaused;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundMusic = MediaPlayer.create(MainActivity.this, R.raw.background_music);
        backgroundMusic.setLooping(true);

        if(!musicPaused) {
            backgroundMusic.start();
        }


        //turn title off

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GamePanel(this));


    }



    @Override
    public void onPause(){
        super.onPause();
        backgroundMusic.release();
        finish();

    }


}
