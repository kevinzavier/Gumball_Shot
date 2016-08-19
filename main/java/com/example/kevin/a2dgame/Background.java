package com.example.kevin.a2dgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kevin on 8/18/16.
 */
public class Background {
    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res){
        image = res;
        dx = GamePanel.MOVESPEED;
    }
    public void update(){
        x+=dx;
        if(x<0){
            x = 0;
        }
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
        /*
        if(x<0){
            canvas.drawBitmap(image, x+=GamePanel.width, y, null);
        }
        */
    }
    public void resize(int width, int height){
        image = Bitmap.createScaledBitmap(image, width, height, true);
    }


}
