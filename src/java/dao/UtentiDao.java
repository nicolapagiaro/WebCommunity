package dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Categoria;
import pojo.Utente;

/**
 * Classe per i metodi crud legati agli utenti
 *
 * @author nicola
 */
public class UtentiDao {

    /**
     * Metodo per prendere la lista degli utenti presenti
     * @param factory
     * @return la lista di oggetti della classe Utente
     */
    public static List<Utente> getUtenti(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Utente> users = (List<Utente>) sessione.createQuery("FROM Utente ORDER BY (nome) ASC, (cognome) ASC").list();
            for(Utente u : users) {
                Hibernate.initialize(u.getCategorie());
            }
            tran.commit();
            return users;
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
     * Metodo che permette di aggiungere un utente al db con la lista delle
     * categorie preferite
     * @param u oggetto utente
     * @param idCategorie lista degli ID di categorie preferite
     * @param factory session factory
     * @return l'id maggiore di 0 se andato a buon fine, se no -1
     */
    public static Utente addUtente(Utente u, Integer[] idCategorie, SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            // scarico la lista delle categorie selezionate
            List<Categoria> cats = sessione
                    .createQuery("FROM Categoria WHERE id IN :list")
                    .setParameterList("list", idCategorie)
                    .list();
            // le aggiungo all'oggetto utente
            u.setCategorie(cats);
            // salvo l'oggetto della base di dati
            sessione.save(u);
            tran.commit();
            return u;
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
     * Metodo per il login dell'utente
     * @param nick nickname utente
     * @param email email dell'utente
     * @param factory session factory
     * @return l'oggetto utente
     */
    public static Utente loginUtente(String nick, String email, SessionFactory factory) {
        Session sessione = factory.openSession();
        Utente utente = (Utente) sessione
                .createQuery("FROM Utente WHERE nickname = :nick AND email = :email")
                .setParameter("nick", nick)
                .setParameter("email", email)
                .uniqueResult();
        sessione.close();
        return utente;
    }

    /**
     * Metodo che permette di aggiungere un utente al db senza categorie
     * preferite
     * @param u oggetto utente
     * @param factory session factory
     * @return 
     */
    public static Utente addUtente(Utente u, SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            // salvo l'oggetto della base di dati
            sessione.save(u);
            tran.commit();
            return u;
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
     * Metodo per aggiornare i dati dell'utente
     * @param u oggeto utente
     * @param cat array con le categorie preferite
     * @param sessionFactory sessio factory
     * @return 
     */
    public static Utente updateUtente(Utente u, Integer[] cat, SessionFactory sessionFactory) {
         Session sessione = sessionFactory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            
            u.setCategorie(null);
            // salvo l'oggetto della base di dati
            sessione.update(u);
            
            if(cat != null) {
                // scarico la lista delle categorie selezionate
                List<Categoria> cats = sessione
                        .createQuery("FROM Categoria WHERE id IN :list")
                        .setParameterList("list", cat)
                        .list();
                // le aggiungo all'oggetto utente
                u.setCategorie(cats);
                
                // salvo l'oggetto della base di dati
                sessione.update(u);
            }
            tran.commit();
            return u;
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
     * Metodo che elimina un utente
     * @param id id utente
     * @param factory session factory
     */
    public static void deleteUtente(int id, SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            // salvo l'oggetto della base di dati
            Utente u = (Utente) sessione.get(Utente.class, id);
            sessione.delete(u);
            tran.commit();
        } catch (HibernateException e) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
    }
}
