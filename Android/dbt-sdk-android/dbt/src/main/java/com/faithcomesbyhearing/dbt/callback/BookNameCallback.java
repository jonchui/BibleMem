package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.BookName;

import java.util.List;

public abstract class BookNameCallback extends Callback<BookName> {
    public abstract void success(BookName bookNames);
    public abstract void failure(Exception e);

    @Override
    public void success(List<BookName> volumes) {
        success(volumes.get(0));
    }
}
