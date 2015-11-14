package com.faithcomesbyhearing.dbt;

import com.faithcomesbyhearing.dbt.model.VerseStart;

import java.util.List;

public abstract class VerseStartCallback extends Callback<VerseStart> {
    public abstract void success(List<VerseStart> volumes);
    public abstract void failure(Exception e);
}
