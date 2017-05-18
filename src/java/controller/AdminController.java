package controller;

import dao.ArtistiDao;
import dao.CategorieDao;
import dao.EventiDao;
import dao.UtentiDao;
import hibernate.HibernateUtil;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.Artista;
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

            // passo alla pagina la lista delle categorie
            map.addAttribute("artisti", ArtistiDao.getArtisti(s));
                
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
                map.addAttribute("artisti", ArtistiDao.getArtisti(s));

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

        sectionActive = 3;

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

        sectionActive = 2;

        return "redirect:/admin";
    }

    /**
     * Metodo per aggiungere un nuovo artista
     *
     * @param map
     * @param request
     * @param nome
     * @param cognome
     * @return
     */
    @RequestMapping(value = "/admin/addArtista", method = RequestMethod.POST)
    public String addArtista(ModelMap map, HttpServletRequest request,
            @RequestParam("nomeA") String nome,
            @RequestParam("cognomeA") String cognome) {

        SessionFactory s = HibernateUtil.getSessionFactory();

        ArtistiDao.addArtista(s, new Artista(nome, cognome));
        
        sectionActive = 1;

        return "redirect:/admin";
    }

    /**
     * Metodo per eliminare un artista
     *
     * @param map
     * @param request
     * @param id id artista
     * @return
     */
    @RequestMapping(value = "/admin/deleteArtista", method = RequestMethod.GET)
    public String deleteArtista(ModelMap map, HttpServletRequest request,
            @RequestParam("id") int id) {

        SessionFactory s = HibernateUtil.getSessionFactory();

        ArtistiDao.deleteArtista(s, id);
        
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

        sectionActive = 3;

        return "redirect:/admin";
    }

    /**
     * Metodo per mandare la mail ad un utente
     *
     * @param map
     * @param request
     * @param to mail del destinatario
     * @param mail testo della mail
     * @return
     */
    @RequestMapping(value = "/admin/sendMail", method = RequestMethod.POST)
    public String sendMail(ModelMap map, HttpServletRequest request,
            @RequestParam("to") String to,
            @RequestParam("mail") String mail) {

        // Sender's email ID needs to be mentioned
        String from = "pagiaro@gmail.com";

        // Get system properties object
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        // Get the default Session object.
        Session mailSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        from, "");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(mailSession);
            
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Messaggio dalla web community");

            // Now set the actual message
            message.setText(mail);

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        
        sectionActive = 4;
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
