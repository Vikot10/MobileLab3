package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    static boolean isCheckedSound = true;
    static boolean isCheckedMusic = true;
    MenuItem menuItem;
    static MediaPlayer soundPlayer;
    static MediaPlayer musicPlayer;
    static MediaPlayer victoryPlayer;
    private static final String PREFS_FILE = "Settings";
    private static final String PREF_SOUND = "Sound";
    private static final String PREF_MUSIC = "Music";
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
        musicPlayer = MediaPlayer.create(this, R.raw.back_music);
        soundPlayer = MediaPlayer.create(this, R.raw.sound);
        victoryPlayer = MediaPlayer.create(this, R.raw.victory);
        musicPlayer.setLooping(true);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        isCheckedSound = settings.getBoolean(PREF_SOUND, false);
        isCheckedMusic = settings.getBoolean(PREF_MUSIC, false);
        if(isCheckedMusic) {
            musicPlayer.start();
        }
        else {
            musicPlayer.pause();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        menuItem = menu.findItem(R.id.sound);
        menuItem.setChecked(isCheckedSound);
        menuItem = menu.findItem(R.id.music);
        menuItem.setChecked(isCheckedMusic);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.sound){
            isCheckedSound = !isCheckedSound;
            item.setChecked(isCheckedSound);
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putBoolean(PREF_SOUND, isCheckedSound);
            prefEditor.apply();
        }
        if (id == R.id.music){
            isCheckedMusic = !isCheckedMusic;
            item.setChecked(isCheckedMusic);
            if(isCheckedMusic) {
                musicPlayer = MediaPlayer.create(this, R.raw.back_music);
                musicPlayer.setLooping(true);
                musicPlayer.start();
            }
            else {
                musicPlayer.pause();
            }
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putBoolean(PREF_MUSIC, isCheckedMusic);
            prefEditor.apply();
        }
        return super.onOptionsItemSelected(item);
    }
}
