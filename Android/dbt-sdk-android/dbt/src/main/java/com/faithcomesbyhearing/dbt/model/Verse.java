package com.faithcomesbyhearing.dbt.model;
/*
book_name
book_id
book_order
chapter_id
chapter_title
paragraph_number
verse_id
verse_text - verse text will contain special markup characters for lightweight presentational purposes. Some of these markup sets include:
 */

import com.google.gson.annotations.SerializedName;

public class Verse {
    @SerializedName("book_name")
    private String bookName;
    @SerializedName("book_id")
    private String bookId;
    @SerializedName("book_order")
    private String bookOrder;
    @SerializedName("chapter_id")
    private String chapterId;
    @SerializedName("chapter_title")
    private String chapterTitle;
    @SerializedName("paragraph_number")
    private String paragraphNumber;
    @SerializedName("verse_id")
    private String verseId;
    @SerializedName("verse_text")
    private String verseText;

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

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getParagraphNumber() {
        return paragraphNumber;
    }

    public void setParagraphNumber(String paragraphNumber) {
        this.paragraphNumber = paragraphNumber;
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
