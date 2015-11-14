package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

/*
dam_id: DAM ID of the volume requested or both NT and OT if a 6 character DAM ID is provided for the search.
book_id: OSIS book code.
chapter_id: The id/number of the chapter
chapter_name: The name of the chapter
default: Specifies if this chapter is the default chapter for the given volume and book.
 */
public class Chapter {

    @SerializedName("dam_id")
    private String damId;
    @SerializedName("book_id")
    private String bookId;
    @SerializedName("chapter_id")
    private String chapterId;
    @SerializedName("chapter_name")
    private String chapterName;
    @SerializedName("default")
    private String def;

    public String getDamId() {
        return damId;
    }

    public void setDamId(String damId) {
        this.damId = damId;
    }

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

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }
}
