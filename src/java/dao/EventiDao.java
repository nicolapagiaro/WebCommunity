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
     * Costruttore per inizializzare la session factory
     * @param factory session factory object
     */
    public EventiDao(SessionFactory factory) {
        EventiDao.factory = factory;
    }

    /**
     * Metodo che restituisce la lista degli eventi
     * @return 
     */
    public List<Evento> getEventi() {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List eventi = (List) sessione.createQuery("FROM EVENTI");
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
