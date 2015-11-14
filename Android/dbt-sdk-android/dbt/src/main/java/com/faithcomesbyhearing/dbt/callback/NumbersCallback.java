package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;

import java.util.HashMap;
import java.util.List;

public abstract class NumbersCallback extends Callback<HashMap<String, String>> {

    public abstract void success(HashMap<String, String> numbers);
    public abstract void failure(Exception e);

    @Override
    public void success(List<HashMap<String, String>> volumes) {
        success(volumes.get(0));
    }
}
