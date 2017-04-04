package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
     * Metodo che permette di aggiungere un utente al db
     *
     * @param u oggetto utente
     * @param factory session factory
     * @return vero se è andata a buon fine, falso se no
     */
    public static boolean addUtente(Utente u, SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            sessione.save(u);
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
