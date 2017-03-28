package controller;

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
    public MainController() {}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        map.put("index", null);
        return "index";
    }
}
