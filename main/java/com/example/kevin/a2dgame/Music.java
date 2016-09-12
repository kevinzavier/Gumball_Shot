package com.example.kevin.a2dgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 9/11/16.
 */
public class Music extends  GameObject{
    private Bitmap image;
    private Bitmap resized;
    private int score;
    private double dya;
    private boolean up;
    private boolean playing = false;
    private final int OFFSET = 70;
    private int half = 60;


    public Music(Bitmap res, int w, int h){
        dy = 0;
        score = 0;
        height = h;
        width = w;
        image = res;
        x = GamePanel.width - 75 - OFFSET;
        y = 80;
        resized = Bitmap.createScaledBitmap(image, 120, 120, true);
    }
    public void setImage(Bitmap res){
        image = res;
        resized = Bitmap.createScaledBitmap(image, 120, 120, true);
    }
    public boolean contains(float xx, float yy){
        if(xx> x + half + half || xx < x + half - half  || yy > y + half + half || yy < y + half - half){
            return false;
        }
        return true;
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(resized, x, y, null);
    }
}
