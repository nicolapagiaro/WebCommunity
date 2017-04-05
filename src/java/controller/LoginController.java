package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Classe LoginController
 * @author Nicola Pagiaro
 */
@Controller
public class LoginController {
    
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