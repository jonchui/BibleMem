package com.faithcomesbyhearing.dbt.callback;

import com.faithcomesbyhearing.dbt.Callback;
import com.faithcomesbyhearing.dbt.model.Organization;

import java.util.List;

public abstract class OrganizationCallback extends Callback<Organization> {
    public abstract void success(List<Organization> volumes);
    public abstract void failure(Exception e);
}
