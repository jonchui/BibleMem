package com.example.claire.biblemem;
import android.app.Application;
import com.faithcomesbyhearing.dbt.Dbt;

/**
 * Created by Claire on 11/14/2015.
 */

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Dbt.setApiKey("cc79c4b62ebfe6cf77f6d07c27d4949a");
    }
}

