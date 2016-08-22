package com.example.kevin.a2dgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

/**
 * Created by kevin on 8/17/16.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int MOVESPEED = 0;
    private MainThread thread;
    private Background bg;
    private Player ball;
    private ArrayList<Goal> goals;
    private Dot dot;
    public static int width;
    public static int height;
    float x = -1;
    float y = -1;
    private float lastx;
    private float lasty;
    public static float eventX;
    public static float eventY;
    private boolean init;
    private boolean valid;


    public GamePanel(Context context){
        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make GamePanel focusable so it can handle events
        setFocusable(true);


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000){
            counter++;
            try{
                thread.setRunning(false);
                thread.join();
                retry = false;

            }catch(InterruptedException e){e.printStackTrace();}

        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
       //start the thread
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.blue));
        ball = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.redball), 200, 200, 1);
        goals = new ArrayList<Goal>();
        goals.add(new Goal(GamePanel.width - 300, GamePanel.height/2 - height));

        dot = new Dot(BitmapFactory.decodeResource(getResources(), R.drawable.blackdot), 100, 100, 1);

        thread.setRunning(true);
        thread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        if(event.getAction()==MotionEvent.ACTION_DOWN){

            if(ball.contains(event.getX(), event.getY())){

                init = true;
            }
            //x= event.getX();
            //y = event.getY();
            //ball.setTouched(true);

            //ball.move((int)(event.getX() - lastx), (int)(event.getY() - lasty)); //move in just one

            //lastx = x;
            //lasty = y;
            /*
            if(!ball.getPlaying()){
                //ball.setPlaying(true);
            }
            else{
                ball.setUp(true);

            }
            */

        }
        if(event.getAction()==MotionEvent.ACTION_MOVE && init){
            x= event.getX();
            y = event.getY();
            //TODO gonna change it, so user can make accidents if they lightly move it
            valid = true;



        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            //Log.i("", "BRUHHHHHHHHHHHHHHHHHHHHHHHHHHH");
            if(init && valid){
                ball.setTouched(true);
            }
            init = false;
        }
        /*
        if(event.getAction()==MotionEvent.ACTION_UP){
            //ball.setUp(false);
            return true;
        }
        */
        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        //updates the background
        bg.update();
        //updates the ball
        ball.update();
        //this is so that we can only drag the ball once
        if(x > 0 && y > 0 && !ball.getTouched()) {
            ball.setImage((int) x, (int) y);
        }
        if(collision(goals.get(0),ball)){
            Log.i("","HAS INTERSECTED");
            goals.remove(0);

        }
        if(ball.getY() > height - 50 ){
            ball.remove();
        }

    }

    public boolean collision(GameObject a, GameObject b){
        if(Rect.intersects(a.getRectangle(), b.getRectangle())){
            Log.i("","HAS INTERSECTED");
            return true;
        }
        return false;
    }


    @Override
    public void draw(Canvas canvas){
        //final float scaleFactorX = width/(WIDTH);
        //final float scaleFactorY = height/(HEIGHT);
        super.draw(canvas);
        if(canvas!=null) {
            final int savedState = canvas.save();
            //canvas.scale(scaleFactorX, scaleFactorY);
            bg.resize(width, height);
            bg.draw(canvas);
            ball.draw(canvas);
            //draw the ball
            goals.get(0).draw(canvas);
            Log.i("", "YESSSSSSSSSSSSSSSSSSSSSSSSSSS");
            dot.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
