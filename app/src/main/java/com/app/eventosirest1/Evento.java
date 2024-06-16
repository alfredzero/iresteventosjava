package com.app.eventosirest1;

public class Evento {
    private final String urlToRegister;
    private String title;
    private String imageUrl; // Example field to store image URL

    public Evento(String title, String imageUrl, String urlToRegister) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.urlToRegister = urlToRegister;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
