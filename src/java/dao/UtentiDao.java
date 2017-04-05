package dao;

import java.util.List;
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
     *
     * @param factory
     * @return la lista di oggetti della classe Utente
     */
    public static List<Utente> getUtenti(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Utente> users = (List<Utente>) sessione.createQuery("FROM Utente").list();
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
    public static int addUtente(Utente u, Integer[] idCategorie, SessionFactory factory) {
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
            int id = (int) sessione.save(u);
            tran.commit();
            return id;
        } catch (HibernateException e) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return -1;
    }
    

    /**
     * Metodo per il login dell'utente
     * @param nick nickname utente
     * @param email email dell'utente
     * @param factory session factory
     * @return l'id maggiore di 0 se andato a buon fine, se no -1
     */
    public static int loginUtente(String nick, String email, SessionFactory factory) {
        Session sessione = factory.openSession();
        int id;
        try {
            // prendo l'id dell'utente
            List query = sessione
                    .createQuery("SELECT id FROM Utente WHERE nickname = :nick AND email = :email")
                    .setParameter("nick", nick)
                    .setParameter("email", email)
                    .list();
            
            if(query.isEmpty()){
                return -1;
            }
            id = (int) query.get(0);
            return id;
        } catch (HibernateException e) {

        } finally {
            sessione.close();
        }
        return -1;
    }
    
    /**
     * Metodo che permette di aggiungere un utente al db senza categorie
     * preferite
     * @param u oggetto utente
     * @param factory session factory
     * @return l'id maggiore di 0 se andato a buon fine, se no -1
     */
    public static int addUtente(Utente u, SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            // salvo l'oggetto della base di dati
            int id = (int) sessione.save(u);
            tran.commit();
            return id;
        } catch (HibernateException e) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return -1;
    }
}
