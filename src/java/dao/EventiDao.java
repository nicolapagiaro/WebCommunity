package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Evento;

/**
 * Classe per i metodi crud legati agli utenti
 *
 * @author FSEVERI\pagiaro3283
 */
public class EventiDao {

    /**
     * Metodo che restituisce la lista degli eventi
     *
     * @param factory
     * @return
     */
    public static List<Evento> getEventi(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Evento> eventi = (List<Evento>) sessione.createQuery("FROM Evento").list();
            tran.commit();
            return eventi;
        } catch (HibernateException e) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return null;
    }

    /**
     * Metodo che restituisce la lista degli eventi ordinati per data più
     * recenti
     *
     * @param factory
     * @return
     */
    public static List<Evento> getMainEventi(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Evento> eventi = (List<Evento>) sessione
                    .createQuery("FROM Evento WHERE dataE > current_date() ORDER BY (dataE) ASC")
                    .list();
            tran.commit();
            return eventi;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return null;
    }

    /**
     * Metodo che restituisce la lista degli eventi con la media più alta di
     * voto
     * @param factory
     * @return
     */
    public static List getMostRatedEventi(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List eventi = (List) sessione
                    .createQuery("FROM VotoCommento")
                    .list();
            tran.commit();
            System.out.println(eventi);
            return eventi;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return null;
    }
}
