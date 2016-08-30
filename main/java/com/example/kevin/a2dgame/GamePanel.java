package com.example.kevin.a2dgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
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
    private ArrayList<Line> lines;
    private Dot dot;
    public static int width;
    public static int height;
    float x = -1;
    float y = -1;
    private boolean newGame = true;
    private boolean init;
    private boolean valid;
    private boolean won;
    private boolean gameOver;
    private boolean getStart = true;
    private int score = 0;
    private int best = 0;
    public final int OFFSET = 70;

    long startTime;
    long currentTime;


    public GamePanel(Context context){
        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);



        //make GamePanel focusable so it can handle events
        setFocusable(true);



        //gets the height and width of the screen
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
                thread = null;

            }catch(InterruptedException e){e.printStackTrace();}

        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
       //start the thread
        thread = new MainThread(getHolder(), this);
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.blue));
        bg.resize(width, height);
        ball = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.redball), 200, 200, 1);
        goals = new ArrayList<Goal>();
        lines = new ArrayList<Line>();
        lines.add(new Line(350f, height/2 + 50));
        goals.add(new Goal(GamePanel.width - 500, GamePanel.height/2));
        //goals.add(new Goal(800,300))
        dot = new Dot(BitmapFactory.decodeResource(getResources(), R.drawable.blackdot), 100, 100, 1);
        //So we dont run two threads
        thread.setRunning(true);
        if (thread.getState() == Thread.State.NEW)
        {
            thread.start();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            newGame = false;

            if(ball.contains(event.getX(), event.getY())){
                init = true;
            }

        }
        if(event.getAction()==MotionEvent.ACTION_MOVE && init){
            //and thus we can change the movement


            x= event.getX();
            y = event.getY();
            lines.remove(0);;
            lines.add(new Line(event.getX(), event.getY()));

            //TODO gonna change it, so user can make accidents if they lightly move it
            valid = true;



        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            if(init && valid){

                ball.setTouched(true);
            }
            init = false;
        }
        return true;

    }

    public void update() {
        //updates the background
        bg.update();
        //updates the ball, which is only doing something on setTOuched(true)
        ball.update();
        goals.get(0).setVertical(false);
        if(score >= 50) {
            goals.get(0).setVertical(true);
            goals.get(0).update();
        }
        else if(score >= 30) {
            goals.get(0).update();
        }
        else{

        }



        //this is so that we can only drag the ball once
        if(x > 0 && y > 0 && !ball.getTouched()) {
            //moves on the drag
            ball.setImage((int) x, (int) y);
        }

        if(collision(goals.get(0),ball)){
            score += 10;
            goals.remove(0);
            goals.add(new Goal(-100,100));
            won = true;
            //goals.remove(0);

        }
        if(ball.getY() > height  && getStart){
            //this is occuring all the time
            startTime = System.nanoTime();
            gameOver = true;
            //this is so starttime will only be counted once
            getStart = false;
            //ball.remove();
        }
        currentTime = System.nanoTime();
        long elapsedTime = (currentTime - startTime)/1000000;
        if(gameOver && (( elapsedTime > 1200))){
            newGame();
            gameOver = false;

        }

    }

    //happens when we win the game
    public void newGame(){
        if(won) {

            goals.remove(0);
            goals.add(new Goal(GamePanel.width - 500, GamePanel.height / 2));
            if(score > best){
                best = score;
            }
        }
        else{
            score = 0;
        }
        won = false;
        ball.setTouched(false);
        ball.setVelocity(true);
        x = 350 + OFFSET;
        y = GamePanel.height/2 + 50;
        ball.resetImage();
        init = false;
        getStart = true;


    }

    public boolean collision(GameObject a, GameObject b){
        if(Rect.intersects(a.getRectangle(), b.getRectangle())){
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

            bg.draw(canvas);
            lines.get(0).draw(canvas);
            ball.draw(canvas);
            //draw the ball
            goals.get(0).draw(canvas);
            dot.draw(canvas);

            canvas.restoreToCount(savedState);

            drawText(canvas);
        }
    }
    public void drawText(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("SCORE: "  + score, 10, height - 100, paint);
        canvas.drawText("BEST: " + best, width - 305, height -100, paint);

        if(newGame){
            Paint paint1 = new Paint();
            paint1.setTextSize(80);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("DRAG THE BALL AND HIT THE TARGET", width/2 - 750, 100, paint1);

        }
    }
}
