package edu.fje.daw2.m07;

import edu.fje.daw2.m07.model.Pronostic;
import edu.fje.daw2.m07.model.Alumne;
import edu.fje.daw2.m07.model.Ciutat;
import edu.fje.daw2.m07.repositoris.M3_AlumneRepositori;
import edu.fje.daw2.m07.repositoris.MeterologiaRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

@SpringBootApplication
public class M07Application {

	public static void main(String[] args) {
		SpringApplication.run(M07Application.class, args);
	}

    /**
     * Classe principal d'Spring
     * utilitza un microservei per accedir a MongoDB.
     * A més realitza una càrrega inicial de dades
     */
    @SpringBootApplication
    @RestController
    public static class M07uf2Application implements CommandLineRunner {

        @Autowired
        private MeterologiaRepositori repositori;

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
            Ciutat ciu1 = new Ciutat("Barcelona");
            ArrayList<Pronostic> pro1 = new ArrayList<Pronostic>();

            // create a clock
            ZoneId zid = ZoneId.of("Europe/Paris");

            // create an LocalDate object using now(zoneId)
            LocalDate lt
                    = LocalDate.now(zid);

            pro1.add(new Pronostic(lt,"23","s"));
            ciu1.setPronostics(pro1);
            repositori.save(ciu1);*/
            for (Ciutat c : repositori.findAll()) {
                System.out.println(c);
            }

        }
    }
}
