package controller;

import dao.CategorieDao;
import hibernate.HibernateUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @param nickname
     * @param nome
     * @param cognome
     * @param email
     * @return 
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String doRegister(@RequestParam("nick") String nickname, 
            @RequestParam("nome") String nome, 
            @RequestParam("cognome") String cognome,
            @RequestParam("email") String email) {
        
        System.out.println(nickname);
        
        
        return "redirect:/";
    }
}