package edu.fje.daw2.m07.model;

import edu.fje.daw2.m07.model.Pronostic;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe entitat que representa un alumne
 * @author sergi.grau@fje.edu
 * @version 1.0 24.02.21
 */
public class Ciutat implements Serializable {

    @Id
    public String id;

    private String nom;
    private ArrayList<Pronostic> pronostics;

    public Ciutat(String nom) {
        this.nom = nom;
        this.pronostics = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Pronostic> getPronostics() {
        return pronostics;
    }

    public void setPronostics(ArrayList<Pronostic> pronostics) {
        this.pronostics = pronostics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciutat ciutat = (Ciutat) o;
        return Objects.equals(id, ciutat.id) && Objects.equals(nom, ciutat.nom) && Objects.equals(pronostics, ciutat.pronostics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, pronostics);
    }
}
