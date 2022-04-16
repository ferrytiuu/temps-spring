package edu.fje.daw2.m07.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe entitat que representa una Ciutat
 */
public class Ciutat implements Serializable {
    private int id;
    private String nom;
    private ArrayList<Pronostic> pronostics;

}
