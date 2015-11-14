package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.AudioLocation;

import java.util.List;

public abstract class AudioLocationCallback extends Callback<AudioLocation> {
    public abstract void success(List<AudioLocation> audioLocations);
    public abstract void failure(Exception e);
}
