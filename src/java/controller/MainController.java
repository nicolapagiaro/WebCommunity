package controller;

import dao.EventiDao;
import hibernate.HibernateUtil;
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
        // passo alla pagina la lista degli eventi
        map.addAttribute("listaEventi", 
                EventiDao.getMainEventi(HibernateUtil.getSessionFactory()));
        
        // controllo se ci sono errori nel login
        if ((request.getSession().getAttribute("controlloLogin")) != null)
            map.addAttribute("control",true);
        return "index";
    }
    
}
