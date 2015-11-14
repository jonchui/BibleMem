package com.faithcomesbyhearing.dbt;

import java.util.List;

public abstract class Callback<T> {
    public abstract void success(List<T> volumes);
    public abstract void failure(Exception e);
}
