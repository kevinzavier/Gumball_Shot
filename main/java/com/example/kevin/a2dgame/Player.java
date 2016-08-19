package com.example.kevin.a2dgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.animation.Animation;

/**
 * Created by kevin on 8/18/16.
 */
public class Player extends  GameObject {
    private Bitmap image;
    private Bitmap resized;
    private int score;
    private double dya;
    private boolean up;
    private boolean playing = false;
    //private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames){
        dy = 0;
        score = 0;
        height = h;
        width = w;
        numFrames = 0;
        image = res;
        x = 100;
        y = GamePanel.height/2 - height/2;
        resized = Bitmap.createScaledBitmap(image, 100, 100, true);




    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(resized, x, y, null);
    }
    public int getScore(){
        return score;
    }
    public boolean getPlaying(){
        return playing;
    }
    public void setPlaying(boolean b){
        playing = b;
    }
    public void resetDya(){
        dya = 0;
    }
    public void resetScore(){
        score = 0;
    }

    public void setUp(boolean b){
        up = b;
    }

    public void update(){
        if(playing) {
            long elapsed = (System.nanoTime() - startTime) / 1000000;
            if (up) {
                dy = (int) (dya -= 1.1);
            } else {
                dy = (int) (dya += 1.1);
            }
            if (dy > 14) {
                dy = 14;
            }
            if (dy < -14) {
                dy = -14;
            }

            y += dy * 2;
            dy = 0;
        }


    }

}
