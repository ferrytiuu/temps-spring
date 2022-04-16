package edu.fje.daw2.m07.controladors;

import edu.fje.daw2.m07.model.Ciutat;
import edu.fje.daw2.m07.model.Alumne;
import edu.fje.daw2.m07.model.Pronostic;
import edu.fje.daw2.m07.repositoris.M3_AlumneRepositori;
import edu.fje.daw2.m07.repositoris.MeterologiaRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Controlador del projecte
 */
@Controller
@SessionAttributes("ciutats")
public class ClimaController {

    /**
     * Repositori
     */
    @Autowired
    private MeterologiaRepositori repositori;

    //Set<Alumne> alumnes = new HashSet<>();

    /**
     * Mostrar totes les ciutats
     *
     * @return Retorna totes les ciutats
     */
    @ModelAttribute("ciutats")
    public List<Ciutat> inicialitzar() {

        List<Ciutat> ciutats = new ArrayList<>();
        for (Ciutat d : repositori.findAll()) {
            ciutats.add(d);
        }
        System.out.println(ciutats);
        return ciutats;
    }

    /**
     * Llistar climes
     *
     * @param model
     * @param ciutats
     * @return Retorna la vista de climes
     */
    @GetMapping("/llistarClimes")
    public String llistarClimes(Model model, @ModelAttribute("ciutats") List<Ciutat> ciutats) {
        model.addAttribute("ciutats", ciutats);
        return "llistarClimes";
    }

    /**
     * Afegir climes
     *
     * @param model
     * @param ciutats
     * @return Retorna el formulari per afegir noves entrades
     */
    @GetMapping("/afegirClimes")
    public String afegirClimes(Model model, @ModelAttribute("ciutats") List<Ciutat> ciutats) {
        model.addAttribute("ciutats", ciutats);
        return "afegirClimes";
    }

    /**
     * Nou Clima
     *
     * @param ciutats
     * @param ciutat
     * @param data
     * @param temperatura
     * @param pronostic
     * @param model
     * @return Crea una nova entrada en la BD
     */
    @PostMapping("/nouClima")
    public String nouClima(@ModelAttribute("ciutats") List<Ciutat> ciutats,
                           @RequestParam String ciutat,
                           @RequestParam("data")
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                           @RequestParam String temperatura,
                           @RequestParam String pronostic,
                           Model model) {
        Ciutat ciu1 = ciutatPerNom(ciutats, ciutat);
        ArrayList<Pronostic> pro1 = ciu1.getPronostics();
        ZoneId zid = ZoneId.of("Europe/Paris");
        pro1.add(new Pronostic(data, temperatura, pronostic));
        ciu1.setPronostics(pro1);

        repositori.save(ciu1);


        HashMap<String, String> icnosPronostics = new HashMap<String, String>();
        icnosPronostics.put("Sol", "wi-day-sunny");
        icnosPronostics.put("Núvol", "wi-cloud");
        icnosPronostics.put("Pluja", "wi-rain");
        icnosPronostics.put("Tempesta", "wi-thunderstorm");
        icnosPronostics.put("Neu", "wi-snow");

        model.addAttribute("icnosPronostics", icnosPronostics);
        return "index";
    }

    /**
     * Troba l'entrada de la ciutat pel seu Nom
     *
     * @param Ciutats
     * @param nomCiutat
     * @return Retorna el nom de la ciutat
     */
    public Ciutat ciutatPerNom(Collection<Ciutat> Ciutats, String nomCiutat) {
        return Ciutats.stream().filter(ciutat -> nomCiutat.equals(ciutat.getNom())).findFirst().orElse(null);
    }

    /**
     * Pronòstics de la ciutat
     *
     * @param ciutat
     * @param ciutats
     * @param model
     * @return Retorna els pronòstics de la ciutat
     */
    @GetMapping("/pronostics/{ciutat}")
    public String singlePathVariable(@PathVariable("ciutat") String ciutat,
                                     @ModelAttribute("ciutats") List<Ciutat> ciutats,
                                     Model model) {
        Ciutat ciu1 = ciutatPerNom(ciutats, ciutat);
        ArrayList<Pronostic> pro1 = ciu1.getPronostics();
        model.addAttribute("pronostics", pro1);

        HashMap<String, String> icnosPronostics = new HashMap<String, String>();
        icnosPronostics.put("Sol", "wi-day-sunny");
        icnosPronostics.put("Núvol", "wi-cloud");
        icnosPronostics.put("Pluja", "wi-rain");
        icnosPronostics.put("Tempesta", "wi-thunderstorm");
        icnosPronostics.put("Neu", "wi-snow");



        model.addAttribute("icnosPronostics", icnosPronostics);

        return "pronostics";
    }

    /**
     * Pronòstics de la ciutat
     * @param id
     * @param ciutats
     * @param model
     * @return Retorna les dades de tots els dies de la ciutat especificada
     */
    @DeleteMapping(value = "/pronostics/{objectID}")
    public String eliminarPronostic(@PathVariable("objectID") String id,
                                    @ModelAttribute("ciutats") List<Ciutat> ciutats,
                                    Model model) {

        Ciutat ciutat_temp = null;
        for (Ciutat ciutat : ciutats) {
            Pronostic pronostic_temp = pronosticByID(ciutat.getPronostics(), id);
            if (pronostic_temp != null) {
                ciutat_temp = ciutat;
                ciutat.getPronostics().remove(pronostic_temp);
                repositori.delete(ciutat_temp);
                repositori.save(ciutat);
                break;
            }

        }
        HashMap<String, String> icnosPronostics = new HashMap<String, String>();
        icnosPronostics.put("Sol", "wi-day-sunny");
        icnosPronostics.put("Núvol", "wi-cloud");
        icnosPronostics.put("Pluja", "wi-rain");
        icnosPronostics.put("Tempesta", "wi-thunderstorm");
        icnosPronostics.put("Neu", "wi-snow");

        model.addAttribute("icnosPronostics", icnosPronostics);
        return "index";

    }

    public Pronostic pronosticByID(Collection<Pronostic> Pronostics, String objectID) {
        return Pronostics.stream().filter(pronostic -> objectID.equals(pronostic.getId())).findFirst().orElse(null);
    }

    /**
     * Editar el pronòstic
     * @param pronosticID
     * @param ciutats
     * @param model
     * @return Retorna la pàgina d'edició del pronòstic especificat
     */
    @GetMapping("/edit/{pronosticID}")
    public String editPronostic(@PathVariable("pronosticID") String pronosticID,
                                @ModelAttribute("ciutats") List<Ciutat> ciutats,
                                Model model) {
        Ciutat ciutat_temp = null;
        for (Ciutat ciutat : ciutats) {
            Pronostic pronostic_temp = pronosticByID(ciutat.getPronostics(), pronosticID);
            if (pronostic_temp != null) {
                ciutat_temp = ciutat;
                model.addAttribute("pronostic", pronostic_temp);
                model.addAttribute("ciutatNom", ciutat_temp.getNom());
                break;
            }

        }
        return "afegirClimes";

    }

    /**
     * Creació de nou pronòstic
     * @param ciutats
     * @param objectID
     * @param ciutat
     * @param data
     * @param temperatura
     * @param pronostic
     * @param model
     * @return Retorna el formulari per crear un nou pronòstic
     */
    @PutMapping("/nouClima")
    public String editPronosticPOST(
            @ModelAttribute("ciutats") List<Ciutat> ciutats,
            @RequestParam String objectID,
            @RequestParam String ciutat,
            @RequestParam("data")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam String temperatura,
            @RequestParam String pronostic,
            Model model) {

        Ciutat ciutat_temp = null;
        for (Ciutat ciutat_0 : ciutats) {
            Pronostic pronostic_temp = pronosticByID(ciutat_0.getPronostics(), objectID);
            if (pronostic_temp != null) {
                ciutat_temp = ciutat_0;
                ciutat_0.getPronostics().remove(pronostic_temp);
                ciutat_0.getPronostics().add(new Pronostic(data, temperatura, pronostic));
                repositori.delete(ciutat_temp);
                repositori.save(ciutat_temp);
                break;
            }

        }
        HashMap<String, String> icnosPronostics = new HashMap<String, String>();
        icnosPronostics.put("Sol", "wi-day-sunny");
        icnosPronostics.put("Núvol", "wi-cloud");
        icnosPronostics.put("Pluja", "wi-rain");
        icnosPronostics.put("Tempesta", "wi-thunderstorm");
        icnosPronostics.put("Neu", "wi-snow");

        model.addAttribute("icnosPronostics", icnosPronostics);
        return "index";

    }
}
