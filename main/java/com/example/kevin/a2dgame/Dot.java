package com.example.kevin.a2dgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 8/19/16.
 */
public class Dot extends GameObject{
    private Bitmap image;
    private Bitmap resized;
    private int score;
    private double dya;
    private boolean up;
    private boolean playing = false;
    private final int OFFSET = 70;


    public Dot(Bitmap res, int w, int h, int numFrames){
        dy = 0;
        score = 0;
        height = h;
        width = w;
        numFrames = 0;
        image = res;
        x = 250 + 75 + OFFSET;
        y = GamePanel.height/2 - height + 100 + 25;
        resized = Bitmap.createScaledBitmap(image, 50, 50, true);
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(resized, x, y, null);
    }

}
