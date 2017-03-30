package controller;

import dao.EventiDao;
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
     * 
     * @param map
     * @return 
     */
    @RequestMapping(value = "/")
    public String index(ModelMap map) {
        EventiDao events = new EventiDao(HibernateUtil.getSessionFactory());
        System.out.println(events.getEventi());
        map.addAttribute("listEventi", events.getEventi());
        return "index";
    }
}
