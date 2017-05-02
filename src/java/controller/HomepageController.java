package controller;

import dao.ArtistiDao;
import dao.CategorieDao;
import dao.EventiDao;
import hibernate.HibernateUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Evento;
import pojo.Utente;
import pojo.VotoCommento;

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
        
        List<Evento> events;

        if ("default".equals(c)) {
            //random gesu balla 
            events = EventiDao.getEventiRandom(s, u);
        } else if ("dc".equals(c)) {
            // passo alla pagina la lista degli eventi in ordine data crescente
            events = EventiDao.getEventiDC(s);
        } else if ("dd".equals(c)) {
            //passo la pagina con eventi data decrescenti 
            events =  EventiDao.getEventiDD(s);
        } else if ("lc".equals(c)) {
            //passo la pagina con eventi lettera crescente
            events =  EventiDao.getEventiLC(s);
        } else if ("ld".equals(c)){
            //passo la pagina con eventi lettera decrescente
            events =  EventiDao.getEventiLD(s);
        }
        else{
            //random gesu balla
            events =  EventiDao.getEventiRandom(s, u);
        }
        
        // aggiungo alla pagina la lista degli eventi
        map.addAttribute("listaEventi", events);
        
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
    
    /**
     * metodo per il caricamento della pagina per visualizzare i commenti ed
     * i voti per quel commento
     * @param map
     * @param request
     * @param id id evento
     * @return
     */
    @RequestMapping(value = "/homepage/evento", method = RequestMethod.GET)
    public String evento(ModelMap map, HttpServletRequest request,
            @RequestParam("id") String id) {
        
        // se non è loggato nessuno
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if(u == null) return "redirect:/";
        
        SessionFactory s = HibernateUtil.getSessionFactory();
        Evento e = null;
        try {
            e = EventiDao.getEvento(s, Integer.parseInt(id));
        }
        catch(NumberFormatException ex ) {
            return "redirect:/homepage?ordine=default";
        }
        
        if(e == null) return "redirect:/homepage?ordine=default";
        request.getSession().setAttribute("idEvento", Integer.parseInt(id));
        List<VotoCommento> v = e.getVotiCommenti();
        
        // vedo se l'utente loggato ha commentato
        boolean commentato = false;
        for(VotoCommento vc : v) {
            if(vc.getUtente().getId() == u.getId()) {
                commentato = true;
                break;
            }
        }
        
        map.addAttribute("evento", e);
        map.addAttribute("voti_commenti", v);
        map.addAttribute("commentato", commentato);
        map.addAttribute("idUtente", u.getId());
        
        return "evento";
    }
    
    /**
     * Metodo per commentare un evento
     * @param map
     * @param request
     * @param commento commento dell'utente
     * @param voto voto dell'utente
     * @return
     */
    @RequestMapping(value = "/homepage/evento/commenta", method = RequestMethod.POST)
    public String commenta(ModelMap map, HttpServletRequest request,
            @RequestParam("commento") String commento, @RequestParam("voto") int voto) {
        
        // se non è loggato nessuno
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if(u == null) return "redirect:/";
        
        int idEvento = (int) request.getSession().getAttribute("idEvento");
        SessionFactory s = HibernateUtil.getSessionFactory();
        
        EventiDao.addCommentoVoto(s, idEvento, u, commento, voto);
        
        return "redirect:/homepage/evento?id=" + idEvento;
    }
    
    /**
     * Metodo per eliminare la recensione dell'utente dall'evento
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/homepage/evento/eliminaRecensione", method = RequestMethod.GET)
    public String eliminaRecensione(ModelMap map, HttpServletRequest request) {
        // se non è loggato nessuno
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if(u == null) return "redirect:/";
        
        int idEvento = (int) request.getSession().getAttribute("idEvento");
        SessionFactory s = HibernateUtil.getSessionFactory();
        
        EventiDao.eliminaCommento(s, idEvento, u);
        return "redirect:/homepage/evento?id=" + idEvento;
    }
}
