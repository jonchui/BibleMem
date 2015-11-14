package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.Verse;

import java.util.List;

public abstract class VerseCallback extends Callback<Verse> {
    public abstract void success(List<Verse> verses);
    public abstract void failure(Exception e);
}
