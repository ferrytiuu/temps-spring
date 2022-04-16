package edu.fje.daw2.m07.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe entitat que representa un alumne
 * @author sergi.grau@fje.edu
 * @version 1.0 24.02.21
 */
public class Ciutat implements Serializable {
    private int id;
    private String nom;
    private ArrayList<Pronostic> pronostics;

}
