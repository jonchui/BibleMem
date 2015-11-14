package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.BookOrder;

import java.util.List;

public abstract class BookOrderCallback extends Callback<BookOrder> {
    public abstract void success(List<BookOrder> volumes);
    public abstract void failure(Exception e);
}
