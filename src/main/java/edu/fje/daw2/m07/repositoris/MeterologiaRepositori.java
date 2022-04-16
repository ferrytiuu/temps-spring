package edu.fje.daw2.m07.repositoris;

import edu.fje.daw2.m07.entities.Ciutat;
import edu.fje.daw2.m07.model.Alumne;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interficie de persistència amb Spring Data i MongoDB.
 * @author sergi.grau@fje.edu
 * @version  1.0 4.4.2019
 */

public interface MeterologiaRepositori extends MongoRepository<Ciutat, String> {

    Ciutat findByNom(String nom);
    List<Ciutat> findByNota(int nota);

    List <Ciutat> deleteByNom(String nom);
    Long deleteAlumneByNom(String nom);

}