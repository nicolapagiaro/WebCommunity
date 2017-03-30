package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Evento;

/**
 * Classe per i metodi crud legati agli utenti
 * @author FSEVERI\pagiaro3283
 */
public class EventiDao {
    private static SessionFactory factory;
    
    /**
     * Metodo che restituisce la lista degli eventi
     * @return 
     */
    public static List<Evento> getEventi() {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List eventi = (List) sessione.createQuery("");
            tran.commit();
            return eventi;
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
