package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Classe MainController
 * @author Nicola Pagiaro
 */
@Controller
public class MainController {

    /**
     * Costruttore vuoto
     */
    public MainController() {
    }

    /**
     * metodo per il caricamento della pagina principale
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map, HttpServletRequest request) {
        // controllo se c'è l'id
        if ((request.getSession().getAttribute("controlloLogin")) != null)
            map.addAttribute("control",true);
        else
            setUpHomePageUsers(map);
        return "index";
    }
    
    /**
     * Metodo per caricare la pagina di default
     * @param map 
     */
    private void setUpHomePageDefault(ModelMap map) {
    }
    
    /**
     * Metodo per caricare la pagina a seconda delle preferenze 
     * dell'utente loggato
     * @param map 
     */
    private void setUpHomePageUsers(ModelMap map) {
    }
}
