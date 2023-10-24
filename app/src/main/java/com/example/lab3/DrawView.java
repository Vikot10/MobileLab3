package com.example.lab3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class DrawView extends View {
    static Game game = new Game();
    Paint p = new Paint();
    public DrawView(Context context){
        super(context);
    }
    public void drawCircle(Canvas canvas, int x, int y){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;
        p.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x + w/8,y + w/8, w/8, p);
    }
    public void drawCross(Canvas canvas, int x, int y){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;
        canvas.drawLine(x,y,x+w/4,y+w/4,p);
        canvas.drawLine(x,y+w/4,x+w/4,y,p);
    }
    public void drawLine(Canvas canvas, int state){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;
        int size = w / 3;
        Paint tempP = new Paint();
        tempP.setColor(Color.WHITE);
        tempP.setStrokeWidth(10);
        int i = -1;
        i = state % 3 == 0 ? 3 : state % 3;
        if(state == 4 || state == 5 || state == 6){
            canvas.drawLine( size / 4,(size / 2) + size * (i-1), w - size / 4, (size / 2) + size * (i-1), tempP);
            MainActivity.victoryPlayer.start();
        }
        if(state == 1 || state == 2 || state == 3){
            canvas.drawLine( (size / 2) + size * (i-1),size / 4, (size / 2) + size * (i-1),w - size / 4, tempP);
            MainActivity.victoryPlayer.start();
        }
        if(state == 7){
            canvas.drawLine( size / 4,size / 4, w - size / 4,w - size / 4, tempP);
            MainActivity.victoryPlayer.start();
        }
        if(state == 8){
            canvas.drawLine( size / 4,w - size / 4, w - size / 4,size / 4, tempP);
            MainActivity.victoryPlayer.start();
        }
    }
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.parseColor("#2A2C2F"));
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;
        p.setColor(Color.WHITE);
        p.setStrokeWidth(10);
        canvas.drawLine(0, w/3, w, w/3, p);
        canvas.drawLine(0, 2*w/3, w, 2*w/3, p);
        canvas.drawLine(w/3, 50, w/3, w, p);
        canvas.drawLine(2*w/3, 50, 2*w/3, w, p);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(game.get(i, j) == 1) drawCircle(canvas, 50+i*w/3, 50+j*w/3);
                if(game.get(i, j) == 2) drawCross(canvas, 50+i*w/3, 50+j*w/3);
            }
        }
        int state = game.check();
        String s = new String();
        s = state ==0 && game.getPlayer() == 1 ? "Ход первого игрока" : "Ход второго игрока";
        if(state !=0 && state!=9 && game.getPlayer() == 1) {
            s = "Победа второго игрока";
            drawLine(canvas, state);
        }
        if(state !=0 && state!=9 && game.getPlayer() == 2) {
            s = "Победа первого игрока";
            drawLine(canvas, state);
        }
        if(state == 9) {
            s = "Ничья";
        }
        Paint tempP = new Paint();
        tempP.setStrokeWidth(10);
        tempP.setTextSize(50);
        tempP.setColor(Color.WHITE);
        canvas.drawText(s, 10, w + 50, tempP);
        canvas.drawRect(0,h*50/60,w,h*59/60,p);
    }
    public boolean onTouchEvent(MotionEvent event){
        if(!game.checkGameOver() && !MainActivity.soundPlayer.isPlaying()){
            float X = event.getX();
            float Y = event.getY();
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int w = displayMetrics.widthPixels;
            int h = displayMetrics.heightPixels;
            int i = 0, j = 0;

            if (Y>w)
                return false;

            i = (int)X / (w/3) % 3;
            j = (int)Y / (w/3) % 3;

            if(game.get(i, j) == 0){
                game.set(i, j);
                game.changePlayer();
                invalidate();
                if(MainActivity.isCheckedSound) {
                    MainActivity.soundPlayer.start();
                }
            }
            return true;
        }
        return false;
    }
}
