package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.VolumeLanguageFamily;

import java.util.List;

public abstract class VolumeLanguageFamilyCallback extends Callback<VolumeLanguageFamily> {
    public abstract void success(List<VolumeLanguageFamily> volumes);
    public abstract void failure(Exception e);
}
