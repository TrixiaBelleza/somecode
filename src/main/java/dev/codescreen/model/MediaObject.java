package dev.codescreen.model;

public class MediaObject {
    private int id;
    private String date;
    private String name;
    private float rating;

    public MediaObject(int id, String date, String name, float rating) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.rating = rating;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
