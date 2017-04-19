/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Artista;
import pojo.Evento;

/**
 *
 * @author FSEVERI\meneghetti3282
 */
public class ArtistiDao {
    
    
    /**
     * Metodo che restituisce la lista degli artisti
     *
     * @param factory
     * @return
     */
    public static List<Artista> getArtisti(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Artista> artisti = (List<Artista>) sessione.createQuery("FROM Artista").list();
            tran.commit();
            System.out.println("CIAONENENENNE");
            return artisti;
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
