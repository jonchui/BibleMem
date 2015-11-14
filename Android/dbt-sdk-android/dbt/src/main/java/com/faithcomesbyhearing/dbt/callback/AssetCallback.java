package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.Asset;

import java.util.List;

public abstract class AssetCallback extends Callback<Asset> {
    public abstract void success(List<Asset> volumes);
    public abstract void failure(Exception e);
}
