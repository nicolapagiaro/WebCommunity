package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Categoria;

/**
 * Classe per i metodi crud legati alle categorie
 *
 * @author nicola
 */
public class CategorieDao {

    /**
     * Metodo per ottenere la lista delle categorie
     *
     * @param factory session factory
     * @return la lista delle categorie
     */
    public static List<Categoria> getCategorie(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Categoria> categorie = (List<Categoria>) sessione.createQuery("FROM Categoria").list();
            tran.commit();
            return categorie;
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
