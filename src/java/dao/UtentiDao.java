package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import pojo.Utente;

/**
 * Classe per i metodi crud legati agli utenti
 * @author nicola
 */
public class UtentiDao {
    private SessionFactory factory;
    
    /**
     * Metodo per prendere la lista degli utenti presenti
     * @return la lista di oggetti della classe Utente
     */
    public static List<Utente> getUtenti() {
        return null;
    }
}
