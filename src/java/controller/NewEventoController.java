/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArtistiDao;
import hibernate.HibernateUtil;
import java.util.Arrays;
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
public class NewEventoController {

    public NewEventoController() {
    }
    
    /**
     * metodo per il caricamento della pagina per creare un nuovo evento
     * @param map
     * @param request
     * @param nomeE nome dell'evento
     * @return
     */
    @RequestMapping(value = "/newEvento", method = RequestMethod.POST)
    public String newEvento(ModelMap map, HttpServletRequest request,
            @RequestParam("nomeE") String nomeE) {
        // se non è loggato nessuno
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if(u == null) return "redirect:/";
        
        SessionFactory s = HibernateUtil.getSessionFactory();
        
        map.addAttribute("nomeE", nomeE);
        // passo alla pagina la lista degli artisti
        map.addAttribute("listaArtisti", ArtistiDao.getArtisti(s));
        return "newEvento";
    }
    
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(ModelMap map, HttpServletRequest request,
            @RequestParam("nuoviArtisti") String nA ,
            @RequestParam("name") String nome, 
            @RequestParam("data") String data,
            @RequestParam("via") String via,
            @RequestParam("provincia") String provincia) {
        // se non è loggato nessuno
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if(u == null) return "redirect:/";
        
        SessionFactory s = HibernateUtil.getSessionFactory();
        
        // array con gli id delle categorie selezionate
        String[] artisti = request.getParameterValues("artistiDB");
        System.out.println(Arrays.toString(artisti));
        if(artisti == null)
            System.out.print("ciaooaoaoa");
        //checckato uguale a on
        if(nA.equals("on")){
            
        }
        
        //map.addAttribute("nomeE", nomeE);
        // passo alla pagina la lista degli artisti
        //map.addAttribute("listaArtisti", ArtistiDao.getArtisti(s));
        //return "newEvento";
        return "gesu";
    }
    
    
    
}
