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
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return null;
    }
    
    /**
     * Metodo che restituisce eventi in ordine data decrescente
     * @param factory
     * @return 
     */
    public static List<Evento> getEventiDD(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Evento> eventi = (List<Evento>) sessione
                    .createQuery("FROM Evento WHERE dataE > current_date() ORDER BY (dataE) DESC")
                    .setMaxResults(15)
                    .list();
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
        
    
        /*prima query*/
        public static List<Evento> EventiFatti(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Evento> eventifatti = (List<Evento>) sessione
                .createQuery("FROM Evento WHERE dataE < current_date() ORDER BY (provincia) ASC")
                .setMaxResults(15)
                .list();
            tran.commit();
            System.out.println(eventifatti);
            return eventifatti;
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
    public static List<Evento> getMostRatedEventi(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Evento> eventi = (List<Evento>) sessione
                    .createQuery("FROM Evento")
                    .setMaxResults(15)
                    .list();
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
     * restiurisce eventi ordinati per lettera crescente
     * @param factory
     * @return 
     */
    public static List<Evento> getEventiLC(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Evento> eventi = (List<Evento>) sessione.createQuery("FROM Evento ORDER BY nome ASC").list();
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
     * metodo che restituisce eventi lettera decrescente
     * @param factory
     * @return 
     */
    public static List<Evento> getEventiLD(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Evento> eventi = (List<Evento>) sessione.createQuery("FROM Evento ORDER BY nome DESC").list();
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
     * metodo che restituisce eventi random
     * @param factory
     * @return 
     */
    public static List<Evento> getEventiRandom(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Evento> eventi = (List<Evento>) sessione.createQuery("FROM Evento ORDER BY RAND()").list();
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
     * Metodo per prendere l'evento con id passato
     * @param factory session factory
     * @param idEvento id dell'evento
     * @return l'oggetto evento
     */
    public static Evento getEvento(SessionFactory factory, int idEvento) {
        Session sessione = factory.openSession();
        Evento e = (Evento) sessione.get(Evento.class, idEvento);
        return e;
    }
}
