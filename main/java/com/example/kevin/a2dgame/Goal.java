package com.example.kevin.a2dgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

/**
 * Created by kevin on 8/19/16.
 */
public class Goal extends GameObject{
    public int r;
    private int score;
    private double dya;
    private boolean up;
    private boolean playing = false;
    private int speed = 5;
    private boolean direction;
    private int count = 0;
    public Goal(int x,int y){
        r = 35;
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
        canvas.drawCircle(x -r , y-r, r + 15, paint);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x -r, y-r, r, paint);

    }
    public void remove(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }
    public void update(){
        //this is to change the speed of the target
        if(direction) {
            x += speed;
            count++;
            if(count==50){
                direction = false;
            }
        }
        else{
            x-=speed;
            count--;
            if(count==-50){
                direction = true;
            }
        }

    }



}
