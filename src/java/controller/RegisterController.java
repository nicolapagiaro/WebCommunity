package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
     * 
     * @param map
     * @param request
     * @return 
     */
    @RequestMapping(value = "/registrazione", method = RequestMethod.GET)
    public String register(ModelMap map, HttpServletRequest request) {
        return "register";
    }
}