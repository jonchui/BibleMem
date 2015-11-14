package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.VolumeLanguage;

import java.util.List;

public abstract class VolumeLanguageCallback extends Callback<VolumeLanguage> {
    public abstract void success(List<VolumeLanguage> volumeLanguages);
    public abstract void failure(Exception e);
}
