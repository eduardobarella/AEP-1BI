package org.example.models;

import java.time.LocalDateTime;

public class HistoricoStatus {
    private Status status;
    private String comentario;
    private LocalDateTime data;

    public HistoricoStatus(Status status, String comentario) {
        this.status = status;
        this.comentario = comentario;
        this.data = LocalDateTime.now();
    }

    public String toString() {
        return data + " - " + status + " - " + comentario;
    }
}