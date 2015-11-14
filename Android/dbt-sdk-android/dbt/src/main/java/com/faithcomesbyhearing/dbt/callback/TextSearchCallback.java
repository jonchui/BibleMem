package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.TextSearchResult;

import java.util.List;

public abstract class TextSearchCallback extends Callback<TextSearchResult> {
    public abstract void success(List<TextSearchResult> search);
    public abstract void failure(Exception e);
}
