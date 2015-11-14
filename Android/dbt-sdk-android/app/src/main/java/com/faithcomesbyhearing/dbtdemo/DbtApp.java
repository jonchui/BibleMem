package com.faithcomesbyhearing.dbtdemo;

import android.app.Application;

import com.faithcomesbyhearing.dbt.Dbt;

public class DbtApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Dbt.setApiKey("Your key here");
    }
}
