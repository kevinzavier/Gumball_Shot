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
    private boolean playing;
    private float lastx;
    private float lasty;
    private int initX;
    private int initY;
    private int deltx;
    private int delty;
    boolean velocity = true;
    public final int OFFSET = 70;
    //private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames){
        dy = 0;
        score = 0;
        height = h;
        width = w;
        numFrames = 0;
        image = res;
        //can change the x value
        x = 250 + OFFSET;
        //to get a little above the middle
        y = GamePanel.height/2 - height/2 + 50;
        initX = x;
        initY = y;
        resized = Bitmap.createScaledBitmap(image, 200, 200, true);




    }
    public boolean contains(float xx, float yy){
        if(xx> x + 100 + 100 || xx < x + 100 - 100  || yy > y + 100 + 100 || yy < y + 100 - 100){
            return false;
        }
        return true;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(resized, x, y, null);

    }
    public void setImage(int x, int y){
        this.x = x - 100;
        this.y = y - 100;


    }
    public void resetImage(){
        this.x = 250 + OFFSET;
        this.y = GamePanel.height/2 - height/2 + 50;
        dy = 0;
        dx = 0;
        deltx = 0;
        delty = 0;
        dya = 0;
    }
    public void move(int xx, int yy){
        x = x + xx;
        y = y + yy;
    }

    public void changeColor(Bitmap res){
        image = res;
        resized = Bitmap.createScaledBitmap(image, 200, 200, true);
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
    public void setVelocity(boolean b){
        velocity = b;
    }
    public boolean getTouched(){
        return touched;
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
    public void remove(){
        resized.recycle();
    }

    public void update(){


        if(touched) {


            if(velocity){
                deltx = getX() - initX;
                delty = getY() - initY;
                Log.i("went through velocity", String.valueOf(deltx));
                Log.i("went through velocity", String.valueOf(delty));


                velocity = false;
            }

            dx = (int)( - deltx /6.2);
            dy += (int)( - delty /3.18);
            y += dy;

            //dy = 0;
            dy = (int) (dya += 2);
            x += dx;
            y += dy;


            long elapsed = (System.nanoTime() - startTime) / 1000000;



        }




    }

}
