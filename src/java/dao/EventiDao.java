package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Evento;
import pojo.VotoCommento;
import util.EventoUtil;

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
                    .setMaxResults(15)
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
                    .createQuery("FROM Evento")
                    .setMaxResults(15)
                    .list();
            tran.commit();
            
            List<EventoUtil> events = new ArrayList<>();
            
            // trovo il voto medio
            for(Object o : eventi) {
                Evento e = (Evento) o;
                List<VotoCommento> voti = e.getVotiCommenti();
                int temp = 0, count = 0;
                for(VotoCommento v : voti) {
                    temp += v.getVoto();
                    count++;
                }
                events.add(new EventoUtil(e, temp/count));
            }
            return events;
        } catch (HibernateException e) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return null;
    }
}
