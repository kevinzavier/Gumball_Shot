package com.example.kevin.a2dgame;

import android.graphics.Bitmap;
import android.view.animation.Animation;

/**
 * Created by kevin on 8/18/16.
 */
public class Player extends  GameObject {
    private Bitmap spritesheet;
    private int score;
    private double dya;
    private boolean;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames){
        x = 100;
        y = GamePanel.HEIGHT/2;
        dy = 0;
        score = 0;
        height = w;

        Bitmap[] image
    }
}
