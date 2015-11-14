package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.AudioPath;

import java.util.List;

public abstract class AudioPathCallback extends Callback<AudioPath> {
    public abstract void success(List<AudioPath> audioPaths);
    public abstract void failure(Exception e);
}
