package com.example.dell.androiddatabaseexample;

public class Book {

    private int id;
    private String name;
    private String date;

    public Book(){}

    public Book(String name, String date) {
        super();
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", date=" + date
                + "]";
    }


}

