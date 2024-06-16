package com.app.eventosirest1;

import java.util.List;

public class ApiResponse {
    private Eventos eventos;

    public Eventos getEventos() {
        return eventos;
    }

    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }

    public static class Eventos {
        private List<Evento> eventos;

        public List<Evento> getEventos() {
            return eventos;
        }

        public void setEventos(List<Evento> eventos) {
            this.eventos = eventos;
        }
    }

    public static class Evento {
        private String id;
        private String host;
        private String title;
        private String description;
        private String image;
        private String date;
        private String urlToRegister;

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getUrlToRegister() {
            return urlToRegister;
        }

        public void setUrlToRegister(String urlToRegister) {
            this.urlToRegister = urlToRegister;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "id='" + id + '\'' +
                    ", host='" + host + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", image='" + image + '\'' +
                    ", date='" + date + '\'' +
                    ", urlToRegister='" + urlToRegister + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "eventos=" + eventos +
                '}';
    }
}
