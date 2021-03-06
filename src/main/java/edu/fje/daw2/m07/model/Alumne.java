package edu.fje.daw2.m07.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Classe entitat que representa un alumne
 * @author sergi.grau@fje.edu
 * @version 1.0 24.02.21
 */
public class Alumne  implements Serializable {

    @Id
    public String id;


    private String nom;
    private int nota;

    public Alumne(){}
    public Alumne(String nom, int nota) {
        this.nom = nom;
        this.nota = nota;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNom() {
        return nom;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Alumne{" +
                "nom='" + nom + '\'' +
                ", nota=" + nota +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alumne alumne = (Alumne) o;

        return nom.equals(alumne.nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }
}
