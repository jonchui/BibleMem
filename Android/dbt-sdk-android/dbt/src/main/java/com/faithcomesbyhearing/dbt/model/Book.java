package com.faithcomesbyhearing.dbt.model;

/*
dam_id_root: First 7 characters of DAM ID, or the full DAM ID if entered.
book_id:  OSIS book code if the volume is a standard bible, or the book order if a story volume.
book_name:  Book name.
book_order: Order of book in collection.
number_of_chapters:  Number of chapters in book.
 */

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("dam_id_root")
    private String damIdRoot;
    @SerializedName("book_id")
    private String bookId;
    @SerializedName("book_name")
    private String bookName;
    @SerializedName("book_order")
    private String bookOrder;
    @SerializedName("number_of_chapters")
    private long numberOfChapters;

    public String getDamIdRoot() {
        return damIdRoot;
    }

    public void setDamIdRoot(String damIdRoot) {
        this.damIdRoot = damIdRoot;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookOrder() {
        return bookOrder;
    }

    public void setBookOrder(String bookOrder) {
        this.bookOrder = bookOrder;
    }

    public long getNumberOfChapters() {
        return numberOfChapters;
    }

    public void setNumberOfChapters(long numberOfChapters) {
        this.numberOfChapters = numberOfChapters;
    }
}
