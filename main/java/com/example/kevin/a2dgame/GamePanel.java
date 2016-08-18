package com.example.kevin.a2dgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by kevin on 8/17/16.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    public static final int WIDTH = 259;
    public static final int HEIGHT = 194;
    public static final int MOVESPEED = 0;
    private MainThread thread;
    private Background bg;

    public GamePanel(Context context){
        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make GamePanel focusable so it can handle events
        setFocusable(true);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();

            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
       //start the thread
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.blue));

        thread.setRunning(true);
        thread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        return super.onTouchEvent(event);
    }

    public void update(){
        bg.update();
    }

    @Override
    public void draw(Canvas canvas){
        final float scaleFactorX = getWidth()/(WIDTH *1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        super.draw(canvas);
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
