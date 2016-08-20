package com.example.kevin.a2dgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
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
    private boolean touched;
    private boolean playing = false;
    private float lastx;
    private float lasty;
    //private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames){
        dy = 0;
        score = 0;
        height = h;
        width = w;
        numFrames = 0;
        image = res;
        x = 150;
        y = GamePanel.height/2 - height/2;
        resized = Bitmap.createScaledBitmap(image, 200, 200, true);




    }
    public boolean contains(float xx, float yy){
        if(xx> x + 100 || xx < x - 100  || yy > y + 100 || yy < y - 100){
            Log.i("", "NOPE");
            return false;
        }
        Log.i("", "yes");
        return true;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(resized, x, y, null);

    }
    public void setImage(int x, int y){
        this.x = x - 100;
        this.y = y - 100;

    }
    public void move(int xx, int yy){
        x = x + xx;
        y = y + yy;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
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
    public void setTouched(boolean b){
        touched = b;
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
                dy = (int) (dya-= 1.1);
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
        if(touched){
            //move((int)(GamePanel.eventX - lastx), (int)(GamePanel.eventY- lasty)); //move in just one

            //lastx = x;
            //lasty = y;
        }


    }

}
