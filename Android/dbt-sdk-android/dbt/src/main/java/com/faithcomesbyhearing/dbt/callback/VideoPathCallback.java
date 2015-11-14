package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.VideoPath;

import java.util.List;

public abstract class VideoPathCallback extends Callback<VideoPath> {
    public abstract void success(List<VideoPath> videoPaths);
    public abstract void failure(Exception e);
}
