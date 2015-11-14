package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

/*
dam_id_root: Seven character DAM ID used to define a book order.
book_order: The absolute book order number. If only six characters of a DAM ID are provided as input, which results in a matching OT and NT book list being returned, the NT books will be numbered with Matthew as 55.
book_code: The OSIS book code for the book.
book_name: The English name of the book.
 */
public class BookOrder {

    @SerializedName("dam_id_root")
    private String damId;
    @SerializedName("book_order")
    private String bookOrder;
    @SerializedName("book_code")
    private String bookCode;
    @SerializedName("book_name")
    private String bookName;

    public String getDamId() {
        return damId;
    }

    public void setDamId(String damId) {
        this.damId = damId;
    }

    public String getBookOrder() {
        return bookOrder;
    }

    public void setBookOrder(String bookOrder) {
        this.bookOrder = bookOrder;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
