package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.Book;

import java.util.List;

public abstract class BookCallback extends Callback<Book> {
    public abstract void success(List<Book> books);
    public abstract void failure(Exception e);
}
