package edu.fje.daw2.m07.controladors;

import edu.fje.daw2.m07.entities.Ciutat;
import edu.fje.daw2.m07.model.Alumne;
import edu.fje.daw2.m07.repositoris.M3_AlumneRepositori;
import edu.fje.daw2.m07.repositoris.MeterologiaRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador principal del projecte
 * Implementa un CRUD per l'entitat alumne. Fa servir contingut estatic i
 * plantilles thymeleaf com a vista
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 24.02.21
 */
@Controller
@SessionAttributes("ciutat")
public class ClimaController {

    @Autowired
    private MeterologiaRepositori repositori;

    //Set<Alumne> alumnes = new HashSet<>();
    @ModelAttribute("ciutat")
    public List<Ciutat> inicialitzar() {

        List<Ciutat> ciutats= new ArrayList<>();
        for (Ciutat d : repositori.findAll()) {
            ciutats.add(d);
        }
        System.out.println(ciutats);
        return ciutats;
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
