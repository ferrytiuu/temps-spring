package edu.fje.daw2.m07;

import edu.fje.daw2.m07.model.Alumne;
import edu.fje.daw2.m07.model.Client;
import edu.fje.daw2.m07.repositoris.M3_AlumneRepositori;
import edu.fje.daw2.m07.repositoris.M4_ClientRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
public class M07Application {

	public static void main(String[] args) {
		SpringApplication.run(M07Application.class, args);
	}

    /**
     * Classe principal d'Spring
     * utilitza un microservei per accedir a MongoDB.
     * A més realitza una càrrega inicial de dades
     * @author sergi.grau@fje.edu
     * @version  1.0 4.4.2019
     */
    @SpringBootApplication
    @RestController
    public static class M07uf2Application implements CommandLineRunner {

        @Autowired
        private M3_AlumneRepositori repositori;

        public static void main(String[] args) {
            SpringApplication.run(M07uf2Application.class, args);
        }

        @RequestMapping(value = "/user")
        public Principal user(Principal principal) {
            return principal;
        }

        @Override
        public void run(String... args) throws Exception {

            //Joc de proves de MongoDB
            /*repositori.deleteAll();
            repositori.save(new Alumne("Sergi",1));
            repositori.save(new Alumne("PAdro",8));
            for (Alumne c : repositori.findAll()) {
                System.out.println(c);
            }
            System.out.println();
            */

            System.out.println(repositori.findByNom("Sergi"));

            for (Alumne c : repositori.findByNota(8)) {
                System.out.println(c);
            }
        }
    }
}
