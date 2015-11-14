package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

public class VerseStart {

    @SerializedName("verse_id")
    private String verseId;
    @SerializedName("verse_start")
    private String verseStart;

    public String getVerseId() {
        return verseId;
    }

    public void setVerseId(String verseId) {
        this.verseId = verseId;
    }

    public String getVerseStart() {
        return verseStart;
    }

    public void setVerseStart(String verseStart) {
        this.verseStart = verseStart;
    }
}
