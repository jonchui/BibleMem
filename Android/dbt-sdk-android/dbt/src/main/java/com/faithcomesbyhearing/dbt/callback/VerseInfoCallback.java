package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.VerseInfo;

import java.util.List;

public abstract class VerseInfoCallback extends Callback<VerseInfo> {
    public abstract void success(List<VerseInfo> volumes);
    public abstract void failure(Exception e);
}
