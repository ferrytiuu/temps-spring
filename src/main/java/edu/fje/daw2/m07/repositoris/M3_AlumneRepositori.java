package edu.fje.daw2.m07.repositoris;

import edu.fje.daw2.m07.model.Alumne;
import edu.fje.daw2.m07.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interficie de persistència amb Spring Data i MongoDB.
 * @author sergi.grau@fje.edu
 * @version  1.0 4.4.2019
 */

public interface M3_AlumneRepositori extends MongoRepository<Alumne, String> {

    Alumne findByNom(String nom);
    List<Alumne> findByNota(int nota);

    List <Alumne> deleteByNom(String nom);
    Long deleteAlumneByNom(String nom);

}