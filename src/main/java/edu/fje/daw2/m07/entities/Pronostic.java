package edu.fje.daw2.m07.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Classe entitat que representa un alumne
 * @author sergi.grau@fje.edu
 * @version 1.0 24.02.21
 */
public class Pronostic implements Serializable {
    private LocalDate data;
    private String temperatura;
    private String estat;

}
