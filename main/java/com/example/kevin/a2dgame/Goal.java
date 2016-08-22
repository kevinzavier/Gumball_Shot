package com.example.kevin.a2dgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by kevin on 8/19/16.
 */
public class Goal extends GameObject{
    private Bitmap image;
    private Bitmap resized;
    public int r;
    private int score;
    private double dya;
    private boolean up;
    private boolean playing = false;
    public Goal(int x,int y){
        r = 40;
        this.x = x;
        this.y = y;
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
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x -r - 4, y- -r - 4, r + 4, paint);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x -r, y -r, r, paint);

    }


}
