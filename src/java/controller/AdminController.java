package controller;

import dao.CategorieDao;
import dao.EventiDao;
import dao.UtentiDao;
import hibernate.HibernateUtil;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.Categoria;

/**
 * Classe che raggruppa tutti i controller delle pagine di amministrazione
 *
 * @author nicola
 */
@Controller
public class AdminController {
    private int sectionActive = 0;

    public AdminController() {
    }

    /**
     * Metodo per il caricamento della pagina di amministrazione
     *
     * @param map
     * @param request
     * @param nome
     * @param pass
     * @return
     */
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String admin(ModelMap map, HttpServletRequest request,
            @RequestParam("nome") String nome,
            @RequestParam("password") String pass) {

        // chiamo tutti i DAO per completare la pagina
        if (pass.equals("admin") && nome.equals("admin")) {
            SessionFactory s = HibernateUtil.getSessionFactory();
            request.getSession().setAttribute("admin", true);

            // passo alla pagina la lista degli utenti
            map.addAttribute("users", UtentiDao.getUtenti(s));

            // passo alla pagina la lista degli eventi
            map.addAttribute("events", EventiDao.getEventi(s));

            // passo alla pagina la lista delle categorie
            map.addAttribute("cats", CategorieDao.getCategorie(s));
            
            // per ricare la pagina con la sezione
            map.addAttribute("section", 0);

            return "admin";
        }

        return "redirect:/";
    }

    /**
     * Per evitare errori
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin1(ModelMap map, HttpServletRequest request) {
        if (request.getSession().getAttribute("admin") != null) {
            if ((boolean) request.getSession().getAttribute("admin")) {
                SessionFactory s = HibernateUtil.getSessionFactory();

                // passo alla pagina la lista degli utenti
                map.addAttribute("users", UtentiDao.getUtenti(s));

                // passo alla pagina la lista degli eventi
                map.addAttribute("events", EventiDao.getEventi(s));

                // passo alla pagina la lista delle categorie
                map.addAttribute("cats", CategorieDao.getCategorie(s));
                
                // per ricare la pagina con la sezione attiva prima
                map.addAttribute("section", sectionActive);

                return "admin";
            }

        }

        return "redirect:/";
    }

    /**
     * Metodo per aggiunger una nuvoa categoria
     *
     * @param map
     * @param request
     * @param nome nome della categoria nuova
     * @return
     */
    @RequestMapping(value = "/admin/addCategoria", method = RequestMethod.POST)
    public String addCategoria(ModelMap map, HttpServletRequest request,
            @RequestParam("nomeC") String nome) {

        SessionFactory s = HibernateUtil.getSessionFactory();

        Categoria c = new Categoria(nome);

        CategorieDao.addCategoria(s, c);
        
        sectionActive = 2;
        
        return "redirect:/admin";
    }
    
    
    /**
     * Metodo per eliminare un evento
     *
     * @param map
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/deleteEvento", method = RequestMethod.GET)
    public String deleteEvento(ModelMap map, HttpServletRequest request,
            @RequestParam("id") int id) {

        SessionFactory s = HibernateUtil.getSessionFactory();

        EventiDao.deleteEvento(s, id);
        
        sectionActive = 1;
        
        return "redirect:/admin";
    }
    
    /**
     * Metodo per eliminare un utente
     *
     * @param map
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/deleteUtente", method = RequestMethod.GET)
    public String deleteUtente(ModelMap map, HttpServletRequest request,
            @RequestParam("id") int id) {

        SessionFactory s = HibernateUtil.getSessionFactory();
        
        UtentiDao.deleteUtente(id, s);
        
        sectionActive = 0;
        
        return "redirect:/admin";
    }
    
    /**
     * Metodo per eliminare una categoria
     *
     * @param map
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/deleteCategoria", method = RequestMethod.GET)
    public String deleteCategoria(ModelMap map, HttpServletRequest request,
            @RequestParam("id") int id) {

        SessionFactory s = HibernateUtil.getSessionFactory();

        CategorieDao.deleteCtegoria(s, id);

        sectionActive = 2;
        
        return "redirect:/admin";
    }
    

    /**
     * Metodo per il uscire dalla pagina di amministrazione
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    public String logoutAdmin(ModelMap map, HttpServletRequest request) {
        if (request.getSession().getAttribute("admin") != null) {
            request.getSession().setAttribute("admin", false);
        }
        return "redirect:/";
    }
}
