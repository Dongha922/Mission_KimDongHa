package com.ll.domain;
public class Quotation {

    public int id;
    public String content;
    public String authorName;

    public Quotation(int id, String content, String authorName) {
        this.id =id;
        this.authorName = authorName;
        this.content = content;
    }
}
