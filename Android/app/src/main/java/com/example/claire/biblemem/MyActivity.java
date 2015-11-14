package com.example.claire.biblemem;

import android.app.AlertDialog;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*
        Dbt.getLibraryVolume(null, "text", null, "Eng", new VolumeCallback() {
            @Override
            public void success(List<Volume> volumes) {
                Log.i("biblemem", "volumes: " + volumes.iterator().toString());
            }

            @Override
            public void failure(Exception e) {
                Log.i("biblemem", "failure: " + e.getLocalizedMessage());
            }
        });
        Dbt.getTextVerse("EN1NIVN1ET", "John", "3", null, null, new VerseCallback() {
            @Override
            public void success(List<Verse> verses) {
//                AlertDialog alertDialog = new AlertDialog("verses: " + verses.toString());
                Log.i("biblemem", "verses: " + verses.iterator().toString());
            }

            @Override
            public void failure(Exception e) {

            }
        });*/
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

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Dbt.getLibraryVolume(null, "text", null, "Eng", new VolumeCallback() {
            @Override
            public void success(List<Volume> volumes) {
Log.i("biblemem","volumes");

            }

            @Override
            public void failure(Exception e) {

            }
        });
    }
}
