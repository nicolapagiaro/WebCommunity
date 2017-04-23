package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Categoria;
import pojo.Evento;
import pojo.Utente;
import pojo.VotoCommento;

/**
 * Classe per i metodi crud legati agli utenti
 *
 * @author FSEVERI\pagiaro3283
 */
public class EventiDao {

    /**
     * Metodo che restituisce la lista degli eventi
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
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return null;
    }

    /**
     * (Prima query UDA)
     * Metodo per visualizzare gli eventi passati già fatti
     * @param factory
     * @return 
     */
    public static List<Evento> eventiFatti(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Evento> eventifatti = (List<Evento>) sessione
                    .createQuery("FROM Evento WHERE dataE < current_date() ORDER BY (provincia) ASC")
                    .setMaxResults(15)
                    .list();
            tran.commit();
            return eventifatti;
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
     * Metodo che restituisce la lista degli eventi con la media più alta di
     * voto
     * DA FARE !!!!
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
     *
     * @param factory
     * @return
     */
    public static List<Evento> getEventiLC(SessionFactory factory) {
        Session sessione = factory.openSession();
        List<Evento> eventi = (List<Evento>) sessione
                .createQuery("FROM Evento ORDER BY nome ASC").list();
        for (Evento e : eventi) {
            Hibernate.initialize(e.getArtisti());
        }
        return eventi;

    }

    /**
     * metodo che restituisce eventi lettera decrescente
     *
     * @param factory
     * @return
     */
    public static List<Evento> getEventiLD(SessionFactory factory) {
        Session sessione = factory.openSession();
        List<Evento> eventi = (List<Evento>) sessione
                .createQuery("FROM Evento ORDER BY nome DESC").list();
        for (Evento e : eventi) {
            Hibernate.initialize(e.getArtisti());
        }
        return eventi;

    }
    
    /**
     * Metodo che restituisce eventi in ordine data decrescente
     *
     * @param factory
     * @return
     */
    public static List<Evento> getEventiDD(SessionFactory factory) {
        Session sessione = factory.openSession();
        List<Evento> eventi = (List<Evento>) sessione
                .createQuery("FROM Evento ORDER BY (dataE) DESC")
                .list();
        for (Evento e : eventi) {
            Hibernate.initialize(e.getArtisti());
        }
        return eventi;
    }
    
    /**
     * Metodo che restituisce eventi in ordine data crescente
     *
     * @param factory
     * @return
     */
    public static List<Evento> getEventiDC(SessionFactory factory) {
        Session sessione = factory.openSession();
        List<Evento> eventi = (List<Evento>) sessione
                .createQuery("FROM Evento ORDER BY (dataE) ASC")
                .list();
        for (Evento e : eventi) {
            Hibernate.initialize(e.getArtisti());
        }
        return eventi;
    }

    /**
     * Metodo che restituisce eventi random per visualizzarli nella homepage dell'utente
     * DA FARE!!!! (mettere prima gli eventi delle categorie che segue)
     * @param factory
     * @param u oggetto utente loggato
     * @return
     */
    public static List<Evento> getEventiRandom(SessionFactory factory, Utente u) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            
            // lista delle categorie dell'utente
            List<Categoria> categorie = sessione
                    .createQuery("SELECT categorie FROM Utente WHERE idUtente = :id")
                    .setInteger("id", u.getId())
                    .list();
            
            List<Evento> eventi = new ArrayList<>();
            for(Categoria c : categorie) {
                eventi.addAll(c.getEventi());
            }
            
            if(eventi.size() < 15) {
                List<Evento> l = (List<Evento>) sessione
                        .createQuery("FROM Evento")
                        .setMaxResults(15 - eventi.size())
                        .list();                
                l.removeAll(eventi);
                eventi.addAll(l);
            }
            
            // inizializzo gli artisti di ogni evento per mostrarli nel jsp
            for (Evento e : eventi) { Hibernate.initialize(e.getArtisti()); }
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

    /**
     * Metodo per aggiungere un commento all'evento
     *
     * @param factory session factory
     * @param idEvento id dell'evento
     * @param u oggetto utente
     * @param commento commento
     * @param voto voto
     * @return true se è andato bene, false se no
     */
    public static boolean addCommentoVoto(SessionFactory factory, int idEvento, Utente u, String commento, int voto) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            Evento e = (Evento) sessione.get(Evento.class, idEvento);
            VotoCommento vc = new VotoCommento(commento, voto, u.getId(), idEvento);
            vc.setEvento(e);
            vc.setUtente(u);
            sessione.save(vc);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return false;
    }
}
