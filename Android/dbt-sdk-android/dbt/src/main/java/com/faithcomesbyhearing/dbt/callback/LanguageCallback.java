package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.Language;

import java.util.List;

public abstract class LanguageCallback extends Callback<Language> {
    public abstract void success(List<Language> volumes);
    public abstract void failure(Exception e);
}
