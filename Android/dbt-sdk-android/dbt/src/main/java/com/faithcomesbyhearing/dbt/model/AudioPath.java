package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

/*
book_id: unique ID for the associated book for this chapter
chapter_id: The id of the chapter
path: relative file path for the audio file
 */
public class AudioPath {
    @SerializedName("book_id")
    String bookId;
    @SerializedName("chapter_id")
    String chapterId;
    String path;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
