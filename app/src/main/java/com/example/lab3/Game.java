package com.example.lab3;

public class Game {
    int [][] a = {{0,0,0}, {0,0,0}, {0,0,0}};
    int player = 1;
    boolean isGameOver = false;
    public void set(int i, int j){
        a[i][j] = player;
    }
    public int get(int i, int j){
        return a[i][j];
    }
    public void changePlayer(){
        player = player == 1 ? 2 : 1;
    }
    public int getPlayer(){
        return player;
    }
    public boolean checkGameOver() {
        return isGameOver;
    }
    public int check(){
        int state = 0;

        if(a[0][0]!=0 && a[0][0]==a[0][1] && a[0][1]==a[0][2]) state = 1; else
        if(a[1][0]!=0 && a[1][0]==a[1][1] && a[1][1]==a[1][2]) state = 2; else
        if(a[2][0]!=0 && a[2][0]==a[2][1] && a[2][1]==a[2][2]) state = 3; else

        if(a[0][0]!=0 && a[0][0]==a[1][0] && a[1][0]==a[2][0]) state = 4; else
        if(a[0][1]!=0 && a[0][1]==a[1][1] && a[1][1]==a[2][1]) state = 5; else
        if(a[0][2]!=0 && a[0][2]==a[1][2] && a[1][2]==a[2][2]) state = 6; else

        if(a[0][0]!=0 && a[0][0]==a[1][1] && a[1][1]==a[2][2]) state = 7; else
        if(a[0][2]!=0 && a[0][2]==a[1][1] && a[1][1]==a[2][0]) state = 8; else

        if(a[0][0]*a[0][1]*a[0][2]*a[1][0]*a[1][1]*a[1][2]*a[2][0]*a[2][1]*a[2][2] != 0) state = 9;

        if (state !=0 ) isGameOver = true;

        return state;
    }
}
