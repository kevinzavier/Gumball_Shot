package com.example.kevin.a2dgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

/**
 * Created by kevin on 8/25/16.
 */
public class Line extends  GameObject {
    public int r;
    private int score;
    private double dya;
    private boolean up;
    private boolean playing = false;
    float startx;
    float starty;
    float endx;
    float endy;
    public Line(float x, float y, float xx, float yy){
        r = 35;
        startx = x;
        starty = y;
        endx = xx;
        endy = yy;
        dy = 0;
        score = 0;
        width = 80;
        height = 80;
        //x = GamePanel.width - 300;
        //y = GamePanel.height/2 - height;
        //resized = Bitmap.createScaledBitmap(image, 80, 80, true);
    }
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawLine(startx, starty, endx, endy, paint);

    }
    public void remove(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }


}
