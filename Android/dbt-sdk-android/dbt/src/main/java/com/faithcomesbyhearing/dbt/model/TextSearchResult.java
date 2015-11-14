package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

public class TextSearchResult {
    @SerializedName("dam_id")
    private String damId;
    @SerializedName("book_name")
    private String bookName;
    @SerializedName("book_id")
    private String bookId;
    @SerializedName("book_order")
    private String bookOrder;
    @SerializedName("chapter_id")
    private String chapterId;
    @SerializedName("verse_id")
    private String verseId;
    @SerializedName("verse_text")
    private String verseText;

    public String getDamId() {
        return damId;
    }

    public void setDamId(String damId) {
        this.damId = damId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookOrder() {
        return bookOrder;
    }

    public void setBookOrder(String bookOrder) {
        this.bookOrder = bookOrder;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getVerseId() {
        return verseId;
    }

    public void setVerseId(String verseId) {
        this.verseId = verseId;
    }

    public String getVerseText() {
        return verseText;
    }

    public void setVerseText(String verseText) {
        this.verseText = verseText;
    }
}
