package controller;

import dao.EventiDao;
import dao.UtentiDao;
import hibernate.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Classe MainController
 * @author Nicola Pagiaro
 */
@Controller
public class MainController {

    /**
     * Costruttore vuoto
     */
    public MainController() {}

    /**
     * Index method
     * @param map
     * @return 
     */
    @RequestMapping(value = "/")
    public String index(ModelMap map) {
        EventiDao events = new EventiDao(HibernateUtil.getSessionFactory());
        UtentiDao users = new UtentiDao(HibernateUtil.getSessionFactory());
        map.addAttribute("listEventi", events.getEventi());
        map.addAttribute("listUtenti", users.getUtenti());
        return "index";
    }
}
