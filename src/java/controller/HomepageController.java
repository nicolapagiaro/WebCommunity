package controller;

import dao.CategorieDao;
import dao.EventiDao;
import dao.UtentiDao;
import hibernate.HibernateUtil;
import static java.sql.JDBCType.NULL;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.Evento;
import pojo.Utente;

/**
 * Controller per la pagina home dell'utente
 *
 * @author nicola
 */
@Controller
public class HomepageController {

    /**
     * metodo per il caricamento della pagina principale dell'utente
     *
     * @param map
     * @param request
     * @param c
     * @return
     */
    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String homepage(ModelMap map, HttpServletRequest request,
            @RequestParam("ordine") String c) {
        SessionFactory s = HibernateUtil.getSessionFactory();

        // passo alla pagina jsp l'oggetto utente
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if (u == null) {
            return "redirect:/";
        }
        map.addAttribute("utente", u);

        if ("default".equals(c)) {
            //random gesu balla 
            //map.addAttribute("listaCategorie", CategorieDao.getCategorieUtente(u, s));
        } else if ("dc".equals(c)) {
            // passo alla pagina la lista degli eventi in ordine data crescente
            map.addAttribute("listaEventi", EventiDao.getMainEventi(s));
        } else if ("dd".equals(c)) {
            //passo la pagina con eventi data decrescenti 
            map.addAttribute("listaEventi", EventiDao.getEventiDD(s));
        } else if ("lc".equals(c)) {
            //passo la pagina con eventi lettera crescente
            map.addAttribute("listaEventi", EventiDao.getEventiLC(s));
        } else if ("ld".equals(c)){
            //passo la pagina con eventi lettera decrescente
            map.addAttribute("listaEventi", EventiDao.getEventiLD(s));
        }
        else{
            //random gesu balla
        }

        map.addAttribute("listaCategorie", CategorieDao.getCategorieUtente(u, s));
        return "homepage";
    }

    /**
     * metodo per il caricamento della pagina di specifiche della categoria
     *
     * @param map
     * @param request
     * @param c id della categoria selezionata
     * @return
     */
    @RequestMapping(value = "/homepage/categoria", method = RequestMethod.GET)
    public String categoria(ModelMap map, HttpServletRequest request,
            @RequestParam("c") String c) {
        // se non è loggato nessuno
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if (u == null) {
            return "redirect:/";
        }

        SessionFactory s = HibernateUtil.getSessionFactory();
        map.addAttribute("categoria", c);
        return "categoria";
    }

    
    /**
     * metodo per il caricamento della pagina per creare un nuovo evento
     * @param map
     * @param request
     * @param nomeE nome dell'evento
     * @return
     */
    @RequestMapping(value = "/homepage/newEvento", method = RequestMethod.POST)
    public String newEvento(ModelMap map, HttpServletRequest request,
            @RequestParam("nomeE") String nomeE, @RequestParam("numeroA") int numeroA) {
        // se non è loggato nessuno
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if(u == null) return "redirect:/";
        
        SessionFactory s = HibernateUtil.getSessionFactory();
        map.addAttribute("numeroA",numeroA);
        map.addAttribute("nomeE", nomeE);
        return "newEvento";

    }
}