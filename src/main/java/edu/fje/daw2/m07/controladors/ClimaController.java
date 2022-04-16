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
import java.util.List;

@Controller
@SessionAttributes("ciutats")
public class ClimaController {

    @Autowired
    private MeterologiaRepositori repositori;

    //Set<Alumne> alumnes = new HashSet<>();
    @ModelAttribute("ciutats")
    public List<Ciutat> inicialitzar() {

        List<Ciutat> ciutats = new ArrayList<>();
        for (Ciutat d : repositori.findAll()) {
            ciutats.add(d);
        }
        System.out.println(ciutats);
        return ciutats;
    }

    @GetMapping("/llistarClimes")
    public String llistarClimes(Model model, @ModelAttribute("ciutats") List<Ciutat> ciutats) {
        model.addAttribute("ciutats", ciutats);
        return "llistarClimes";
    }

    @GetMapping("/afegirClimes")
    public String afegirClimes(Model model, @ModelAttribute("ciutats") List<Ciutat> ciutats) {
        model.addAttribute("ciutats", ciutats);
        return "afegirClimes";
    }

    @PostMapping("/nouClima")

    public String nouClima(@ModelAttribute("ciutats") List<Ciutat> ciutats,
                           @RequestParam String ciutat,
                           @RequestParam("data")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                           @RequestParam String temperatura,
                           @RequestParam String pronostic,
                           Model model) {
        Ciutat ciu1 = ciutatPerNom(ciutats,ciutat);
        ArrayList<Pronostic> pro1 = ciu1.getPronostics();
        ZoneId zid = ZoneId.of("Europe/Paris");
        pro1.add(new Pronostic(data, temperatura, pronostic));
        ciu1.setPronostics(pro1);

        repositori.save(ciu1);


        //if(alumnes == null) alumnes = new ArrayList<Alumne>();
        /*alumnes.add(a);
        if (!model.containsAttribute("alumnes")) {
            model.addAttribute("alumnes", alumnes);
        }*/
        return "index";
    }

    public Ciutat ciutatPerNom(Collection<Ciutat> Ciutats, String nomCiutat) {
        return Ciutats.stream().filter(ciutat -> nomCiutat.equals(ciutat.getNom())).findFirst().orElse(null);
    }

    /*

    @PostMapping("/afegirAlumne")
    public String afegirAlumne(@ModelAttribute("alumnes") List<Alumne> alumnes,
                               @RequestParam String nom,
                               @RequestParam(defaultValue = "0", required = false) int nota,
                               Model model) {
        Alumne a = new Alumne(nom, nota);
        repositori.save(a);
        //if(alumnes == null) alumnes = new ArrayList<Alumne>();
        alumnes.add(a);
        if (!model.containsAttribute("alumnes")) {
            model.addAttribute("alumnes", alumnes);
        }
        return "llistarAlumnes";
    }



    @PostMapping("/esborrarAlumne")
    public String esborrarAlumne(
            @ModelAttribute("alumnes") List<Alumne> alumnes,
            @RequestParam String nom,
            @RequestParam(defaultValue = "0", required = false) int nota,
            Model model) {
        alumnes.remove(new Alumne(nom, nota));
        repositori.deleteAlumneByNom(nom);
        model.addAttribute("alumnes", alumnes);
        return "llistarAlumnes";
    }

    @PostMapping("/modificarAlumne")
    public String modificarAlumne(
            @ModelAttribute("alumnes") List<Alumne> alumnes,
            @RequestParam String nom,
            @RequestParam(defaultValue = "0", required = false) int nota,
            Model model) {
        alumnes.remove(new Alumne(nom, nota));
        alumnes.add(new Alumne(nom, nota));
        repositori.deleteAlumneByNom(nom);
        repositori.save(new Alumne(nom, nota));
        model.addAttribute("alumnes", alumnes);
        return "llistarAlumnes";
    }
    @GetMapping("/llistarAlumnes")
    public String llistarAlumnes(Model model, @ModelAttribute("alumnes") List<Alumne> alumnes) {
        model.addAttribute("alumnes", alumnes);
        return "llistarAlumnes";
    }

     */


}
