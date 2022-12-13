package com.example.app;

public class News {
    String name;
    int image;
    int likes;

    public News(String name, int image, int likes) {
        this.image = image;
        this.name = name;
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }
}

