package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.LibraryVersion;

import java.util.List;

public abstract class LibraryVersionCallback extends Callback<LibraryVersion> {
    public abstract void success(List<LibraryVersion> volumes);
    public abstract void failure(Exception e);
}
