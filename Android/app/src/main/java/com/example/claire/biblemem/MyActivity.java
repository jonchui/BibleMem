package com.example.claire.biblemem;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Application;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

import com.faithcomesbyhearing.dbt.Dbt;
import com.faithcomesbyhearing.dbt.callback.VerseCallback;
import com.faithcomesbyhearing.dbt.callback.VolumeCallback;
import com.faithcomesbyhearing.dbt.model.Verse;
import com.faithcomesbyhearing.dbt.model.Volume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform action on click
                /*Dbt.getLibraryVolume(null, "text", null, "Eng", new VolumeCallback() {
                    @Override
                    public void success(List<Volume> volumes) {
                        Log.i("biblemem","volumes");
                    }

                    @Override
                    public void failure(Exception e) {

                    }
                });*/

                //Intent intent = new Intent(this, DisplayMessageActivity.class);
                EditText editTextBook = (EditText) findViewById(R.id.edit_message_book);
                EditText editTextChapterNumber = (EditText) findViewById(R.id.edit_message_chapter_number);
                EditText editTextVerseNumber = (EditText) findViewById(R.id.edit_message_verse_number);
                String messageBook = editTextBook.getText().toString();
                String messageChapterNumber = editTextChapterNumber.getText().toString();
                String messageVerseNumber = editTextVerseNumber.getText().toString();
                Log.i("biblemem", "Book: " + messageBook);
                Log.i("biblemem", "Chapter Number: " + messageChapterNumber);
                Log.i("biblemem", "Verse Number: " + messageVerseNumber);

                //Toast.makeText(getApplicationContext(), "Sent! " + messageBook + " " + messageChapterNumber +  " " + messageVerseNumber, Toast.LENGTH_LONG).show();

                final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.john1_engesvn2da);
                mp.start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 10 seconds
                        mp.stop();
                    }
                }, 10000);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
