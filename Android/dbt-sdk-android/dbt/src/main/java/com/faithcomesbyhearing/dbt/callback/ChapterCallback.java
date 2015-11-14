package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.Chapter;

import java.util.List;

public abstract class ChapterCallback extends Callback<Chapter> {
    public abstract void success(List<Chapter> volumes);
    public abstract void failure(Exception e);
}
