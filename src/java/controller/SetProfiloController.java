/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategorieDao;
import dao.UtentiDao;
import hibernate.HibernateUtil;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.Utente;

/**
 *
 * @author FSEVERI\meneghetti3282
 */
@Controller
public class SetProfiloController {

    public SetProfiloController() {
    }

    /**
     * Metodo che carica la pagina per modifcare il profilo
     * @param map
     * @param request
     * @return 
     */
    @RequestMapping(value = "/homepage/setProfilo", method = RequestMethod.GET)
    public String setProfilo(ModelMap map, HttpServletRequest request) {
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if (u == null) {
            return "redirect:/";
        }
        SessionFactory s = HibernateUtil.getSessionFactory();
        
        // mappo alla pagina l'oggetto utente
        map.addAttribute("u", u);
        
        
        map.addAttribute("catChecked", CategorieDao.getCategorieUtente(u, s));
        map.addAttribute("cat", CategorieDao.getCategorieNoCheck(u, s));
        
        return "modificaProfilo";
    }
    
    /**
     * Metodo per rendere effettive le modifiche nel database
     * @param map
     * @param request
     * @param nome
     * @param cognome
     * @param nickname
     * @param email
     * @return 
     */
    @RequestMapping(value = "/homepage/doSetProfilo", method = RequestMethod.POST)
    public String doSetProfilo(ModelMap map, HttpServletRequest request,
            @RequestParam("nome") String nome,
            @RequestParam("cognome") String cognome,
            @RequestParam("nickname") String nickname,
            @RequestParam("email") String email) {

        Utente u = (Utente) request.getSession().getAttribute("utente");
        if (u == null) {
            return "redirect:/";
        }
        SessionFactory s = HibernateUtil.getSessionFactory();
        
       // setto i nuovi attributi dell'utente
        u.setNome(nome);
        u.setCognome(cognome);
        u.setNickname(nickname);
        u.setEmail(email);

        // array con gli id delle categorie selezionate
        String[] idCat = request.getParameterValues("cat");

        // controllo se ha selezionate delle categorie preferite
        if (idCat != null) {
            Integer[] cat = new Integer[idCat.length];
            for (int i = 0; i < cat.length; i++) {
                cat[i] = Integer.parseInt(idCat[i]);
            }
            // aggiorno l'utente con altre categorie preferite
            UtentiDao.updateUtente(u, cat, s);
        }
        else {
            // aggiorno l'utente senza categorie preferite
            UtentiDao.updateUtente(u, null, s);
        }

        return "redirect:/homepage?ordine=default";
    }
}
