package com.faithcomesbyhearing.dbt.model;

/*
version_code: The three letter version code.
version_name: The version name in native language.
version_english: The version name in English.
 */

import com.google.gson.annotations.SerializedName;

public class LibraryVersion {

    @SerializedName("version_code")
    private String versionCode;
    @SerializedName("version_name")
    private String versionName;
    @SerializedName("version_english")
    private String versionEnglish;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionEnglish() {
        return versionEnglish;
    }

    public void setVersionEnglish(String versionEnglish) {
        this.versionEnglish = versionEnglish;
    }
}
