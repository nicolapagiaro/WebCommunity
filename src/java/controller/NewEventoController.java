/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArtistiDao;
import dao.CategorieDao;
import dao.EventiDao;
import hibernate.HibernateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
 *
 * @author FSEVERI\meneghetti3282
 */
@Controller
public class NewEventoController {

    public NewEventoController() {
    }

    /**
     * metodo per il caricamento della pagina per creare un nuovo evento
     *
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
        if (u == null) {
            return "redirect:/";
        }

        SessionFactory s = HibernateUtil.getSessionFactory();

        map.addAttribute("nomeE", nomeE);
        // passo alla pagina la lista degli artisti
        map.addAttribute("listaArtisti", ArtistiDao.getArtisti(s));
        map.addAttribute("listaCategorie", CategorieDao.getCategorie(s));
        return "newEvento";
    }

    @RequestMapping(value = "/homepage/newEvento/uploadNewArtist", method = RequestMethod.POST)
    public String uploadNewArtist(ModelMap map, HttpServletRequest request,
            @RequestParam("categoria") int categoria,
            @RequestParam("name") String nome,
            @RequestParam("data") String data,
            @RequestParam("via") String via,
            @RequestParam("provincia") String provincia,
            @RequestParam("nA") int numArt) throws ParseException {
        // se non è loggato nessuno
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if (u == null) {
            return "redirect:/";
        }

        SessionFactory s = HibernateUtil.getSessionFactory();
        Date dataD = parseData(data);
        Evento e = new Evento(nome,dataD,via,provincia);
        // prendo gli artisti
        String[] ar = request.getParameterValues("artistiDB");
        if(ar != null) {
            Integer[] artistiDB = new Integer[ar.length];
            for(int i=0; i<ar.length; i++) {
                artistiDB[i] = Integer.parseInt(ar[i]);
            }
            EventiDao.addEvento(e, artistiDB, categoria, s);
        }
        else
            EventiDao.addEvento(e, categoria, s);
        
        map.addAttribute("e", e);
        map.addAttribute("numArt",numArt);
        
        
        
        return "newArtisti";
    }
    
    /**
     * Metodo per l'aggiunta dell'evento senza aggiungere nuovi artisti al database
     * FATTO (Funziona)
     * @param map
     * @param request
     * @param categoria
     * @param nome
     * @param data
     * @param via
     * @param provincia
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/homepage/newEvento/upload", method = RequestMethod.POST)
    public String upload(ModelMap map, HttpServletRequest request,
            @RequestParam("categoria") int categoria,
            @RequestParam("name") String nome,
            @RequestParam("data") String data,
            @RequestParam("via") String via,
            @RequestParam("provincia") String provincia) throws ParseException {
        // se non è loggato nessuno
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if (u == null) {
            return "redirect:/";
        }
        
        SessionFactory s = HibernateUtil.getSessionFactory();
        Date dataD = parseData(data);
        Evento e = new Evento(nome,dataD,via,provincia);
        

        // prendo gli artisti
        String[] ar = request.getParameterValues("artistiDB");
        if(ar != null) {
            Integer[] artistiDB = new Integer[ar.length];
            for(int i=0; i<ar.length; i++) {
                artistiDB[i] = Integer.parseInt(ar[i]);
            }
            EventiDao.addEvento(e, artistiDB, categoria, s);
        }
        else
            EventiDao.addEvento(e, categoria, s);
        return "redirect:/homepage?ordine=default";
    }
    
    /**
     * Metodo per convertire la stringa con la data in un oggetto Date
     * @param data stringa con la data
     * @return l'oggetto Date
     * @throws ParseException boh
     */
    private Date parseData(String data) throws ParseException {
        String month = data.split(" ")[1].split(",")[0];
        Date date = new SimpleDateFormat("MMMM", Locale.ITALIAN).parse(month);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int meseN = cal.get(Calendar.MONTH);
        
        int giorno = Integer.parseInt(data.split(" ")[0]);
        int anno = Integer.parseInt(data.split(" ")[2]);
        
        Calendar c = Calendar.getInstance();
        c.set(anno, meseN, giorno);
        return c.getTime();
    }

}
