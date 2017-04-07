package controller;

import dao.EventiDao;
import dao.UtentiDao;
import hibernate.HibernateUtil;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojo.Utente;

/**
 * Controller per la pagina home dell'utente
 * @author nicola
 */
@Controller
public class HomepageController {
    
    /**
     * metodo per il caricamento della pagina principale dell'utente
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String homepage(ModelMap map, HttpServletRequest request) {
        SessionFactory s = HibernateUtil.getSessionFactory();
        
        // passo alla pagina jsp l'oggetto utente
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if(u == null)
            return "redirect:/";
        map.addAttribute("utente", u);
        
        // passo alla pagina la lista degli eventi
        map.addAttribute("listaEventi", EventiDao.getMainEventi(s));
        return "homepage";
    }
}
