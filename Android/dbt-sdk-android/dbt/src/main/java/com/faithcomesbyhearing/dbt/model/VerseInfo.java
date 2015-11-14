package com.faithcomesbyhearing.dbt.model;

import java.util.List;

/*
verse_id: id of verse
verse_start: time start in seconds of the verse
 */
public class VerseInfo {

    private List<String> bookId;
//    private String chapterId;
//    private List<String> verseId;

    public List<String> getBookId() {
        return bookId;
    }

    public void setBookId(List<String> bookId) {
        this.bookId = bookId;
    }

//    public String getChapterId() {
//        return chapterId;
//    }
//
//    public void setChapterId(String chapterId) {
//        this.chapterId = chapterId;
//    }
//
//    public List<String> getVerseId() {
//        return verseId;
//    }
//
//    public void setVerseId(List<String> verseId) {
//        this.verseId = verseId;
//    }
}
