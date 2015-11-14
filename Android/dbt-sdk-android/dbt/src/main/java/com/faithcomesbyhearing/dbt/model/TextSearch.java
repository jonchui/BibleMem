package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TextSearch {

    @SerializedName("total_results")
    private long resultsReturned;
    private List<TextSearchResult> results;

    public long getResultsReturned() {
        return resultsReturned;
    }

    public void setResultsReturned(long resultsReturned) {
        this.resultsReturned = resultsReturned;
    }

    public List<TextSearchResult> getResults() {
        return results;
    }

    public void setResults(List<TextSearchResult> results) {
        this.results = results;
    }
}
