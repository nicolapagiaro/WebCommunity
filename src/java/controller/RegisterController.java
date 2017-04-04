package controller;

import dao.CategorieDao;
import dao.UtentiDao;
import hibernate.HibernateUtil;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.Utente;

/**
 * Classe RegisterController
 * @author Nicola Pagiaro
 */
@Controller
public class RegisterController {

    /**
     * Costruttore vuoto
     */
    public RegisterController() {
    }
    
    /**
     * metodo per la creazione della pagina di registrazione
     * @param map mappa con i modelli
     * @param request http request
     * @return il nome della vista
     */
    @RequestMapping(value = "/registrazione", method = RequestMethod.GET)
    public String register(ModelMap map, HttpServletRequest request) {
        map.addAttribute("listaCategorie", 
                CategorieDao.getCategoria(HibernateUtil.getSessionFactory()));
        return "register";
    }
    
    /**
     * Metodo per la registrazione di un utente alla web community
     * @param request http request
     * @param nickname nickname utente
     * @param nome nome utente
     * @param cognome cognome utente
     * @param email email utente
     * @return il nome della vista
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String doRegister(HttpServletRequest request,
            @RequestParam("nick") String nickname, 
            @RequestParam("nome") String nome, 
            @RequestParam("cognome") String cognome,
            @RequestParam("email") String email) {
        
        
        
        // array con gli id delle categorie selezionate
        String[] idCategorieSelezionate = request.getParameterValues("categorie");
        Utente u = new Utente(nickname, nome, cognome, email);
        
        if(UtentiDao.addUtente(u, HibernateUtil.getSessionFactory()))
            return "redirect:/";
        
        return "register";
    }
}
