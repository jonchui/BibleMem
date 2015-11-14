package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.VideoLocation;

import java.util.List;

public abstract class VideoLocationCallback extends Callback<VideoLocation> {
    public abstract void success(List<VideoLocation> videoLocations);
    public abstract void failure(Exception e);
}
