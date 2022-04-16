package edu.fje.daw2.m07.model;

import org.bson.codecs.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe entitat que representa un pronòstic
 */
public class Pronostic implements Serializable {

    @Id
    public String id;

    private LocalDate data;
    private String temperatura;
    private String estat;

    public Pronostic(LocalDate data, String temperatura, String estat) {
        this.id = String.valueOf(new ObjectId());
        this.data = data;
        this.temperatura = temperatura;
        this.estat = estat;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }
}
