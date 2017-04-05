package controller;

import dao.CategorieDao;
import dao.UtentiDao;
import hibernate.HibernateUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.Utente;

/**
 * Classe LoginController
 * @author Nicola Pagiaro
 */
@Controller
public class LoginController {
    
    /**
     * metodo per la creazione della pagina di login
     * @param map mappa con i modelli
     * @param request http request
     * @return il nome della vista
     */
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String register(ModelMap map, HttpServletRequest request) {      
        return "login";
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
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request,
            @RequestParam("nick") String nickname, 
            @RequestParam("email") String email) {

        
        int idUtente = UtentiDao
                .loginUtente(nickname, email, HibernateUtil.getSessionFactory());
        
        if(idUtente != -1) {
            request.getSession().setAttribute("idUtente", idUtente);
            return "redirect:/";
        }
        
        //da mettere la pagina post-login
        return "login";
    }
    
    
    /**
     * Metodo per eseguire il logout
     * @param map mappa degli attributi
     * @param request http request
     * @return il nome della vista
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap map, HttpServletRequest request) {
        request.getSession().removeAttribute("idUtente");
        map.addAttribute("idUtente", false);
        return "redirect:/";
    }
}