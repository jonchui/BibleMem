package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.Volume;

import java.util.List;

public abstract class VolumeCallback extends Callback<Volume> {
    public abstract void success(List<Volume> volumes);
    public abstract void failure(Exception e);
}
