package edu.fje.daw2.m07.controladors;

import edu.fje.daw2.m07.model.Alumne;
import edu.fje.daw2.m07.model.Ciutat;
import edu.fje.daw2.m07.repositoris.M3_AlumneRepositori;
import edu.fje.daw2.m07.repositoris.MeterologiaRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private MeterologiaRepositori repositori;

    /**
     * indexController
     * @param model
     * @return Retorna la vista index
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index(Model model) {
        System.out.println("..............hit");

        List<Ciutat> ciutats = new ArrayList<>();
        for (Ciutat d : repositori.findAll()) {
            ciutats.add(d);
        }
        System.out.println(ciutats);


        model.addAttribute("ciutats", ciutats);

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
