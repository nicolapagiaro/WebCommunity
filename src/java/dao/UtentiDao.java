package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Utente;

/**
 * Classe per i metodi crud legati agli utenti
 * @author nicola
 */
public class UtentiDao {
    
    /**
     * Metodo per prendere la lista degli utenti presenti
     * @param factory
     * @return la lista di oggetti della classe Utente
     */
    public List<Utente> getUtenti(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Utente> users = (List<Utente>) sessione.createQuery("FROM Utente").list();
            tran.commit();
            return users;
        }
        catch(HibernateException e) {
            if (tran != null) tran.rollback();
        }
        finally {
            sessione.close();
        }
        return null;
    }
}
