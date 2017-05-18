package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Artista;
import pojo.Categoria;
import pojo.ChiavePrimaria;
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
     * Metodo per aggiungere un evento alla base di dati con degli artisti
     * @param e evento
     * @param idArt id degli artisti
     * @param idCat id della categoria
     * @param factory
     * @return 
     */
     public static Evento addEvento(Evento e, Integer[] idArt, int idCat, SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            // scarico la lista delle categorie selezionate
            List<Artista> arts = sessione
                    .createQuery("FROM Artista WHERE id IN :list")
                    .setParameterList("list", idArt)
                    .list();
            Categoria c = (Categoria) sessione.get(Categoria.class, idCat);
            e.setCategoria(c);
            // salvo l'oggetto della base di dati
            sessione.save(e);
            // aggiungo ad ogni artista l'evento a cui partecipano
            // (altrimenti non va, boh)
            for(Artista a : arts) {
                a.getEventi().add(e);
                sessione.update(a);
            }
            tran.commit();
            return e;
        } catch (HibernateException ciao) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return null;
    }
     
     
     /**
     * Metodo per settare a un evento nuovi artisti e creare nuovi artisti
     * @param e evento
     * @param art
     * @param factory
     * @return 
     */
     public static Evento setEventoNewArt(Evento e, List<Artista> art, SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            for(int i = 0; i< art.size(); i++){
                art.get(i).getEventi().add(e);
                sessione.save(art.get(i));
            }
            tran.commit();
            return e;
        } catch (HibernateException ciao) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return null;
    }
     
     
     
     
     /**
      * Metodo per aggiungere un evento senza alcun artista
      * @param e evento
      * @param idCat id categoria
      * @param factory
      * @return 
      */
     public static Evento addEvento(Evento e, int idCat, SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            Categoria c = (Categoria) sessione.get(Categoria.class, idCat);
            e.setCategoria(c);
            // salvo l'oggetto della base di dati
            sessione.save(e);
            tran.commit();
            return e;
        } catch (HibernateException ciao) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
        return null;
    }
    
    

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
            for(Evento e : eventi) {
                Hibernate.initialize(e.getArtisti());
                Hibernate.initialize(e.getCategoria());
            }
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
            for(Evento e : eventi) {
                Hibernate.initialize(e.getArtisti());
                Hibernate.initialize(e.getCategoria());
            }
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
     * (Prima query UDA) Metodo per visualizzare gli eventi passati già fatti
     *
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
     * Metodo che restituisce la lista degli eventi con la media più alta di voti
     * @param factory
     * @return
     */
    public static List<Evento> getMostRatedEventi(SessionFactory factory) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            List<Integer> eventiID = (List<Integer>) sessione
                    .createSQLQuery("SELECT E.id\n"
                            + "FROM EVENTI E, VOTO_COMMENTO V \n"
                            + "WHERE E.id = V.idEvento\n"
                            + "GROUP BY(E.id)\n"
                            + "ORDER BY (categoria) ASC, (nome) ASC")
                    .list();
            List<Evento> eventi = (List<Evento>) sessione
                    .createQuery("FROM Evento WHERE id in :list")
                    .setParameterList("list", eventiID)
                    .setMaxResults(15)
                    .list();
            
            // inizializzo i voti e commenti di ogni evento per mostrarli nel jsp
            for(Evento e : eventi) {
                Hibernate.initialize(e.getVotiCommenti());
            }
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
     * Metodo che restituisce eventi random per visualizzarli nella homepage
     * dell'utente DA FARE!!!! (mettere prima gli eventi delle categorie che
     * segue)
     *
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
            for (Categoria c : categorie) {
                eventi.addAll(c.getEventi());
            }

            if (eventi.size() < 15) {
                List<Evento> l = (List<Evento>) sessione
                        .createQuery("FROM Evento")
                        .setMaxResults(15 - eventi.size())
                        .list();
                l.removeAll(eventi);
                eventi.addAll(l);
            }

            // inizializzo gli artisti di ogni evento per mostrarli nel jsp
            for (Evento e : eventi) {
                Hibernate.initialize(e.getArtisti());
            }
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
     *
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

    /**
     * Metodo che elimina il commento dell'utente su un determinato evento
     *
     * @param factory
     * @param idEvento
     * @param u
     */
    public static void eliminaCommento(SessionFactory factory, int idEvento, Utente u) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            VotoCommento vc = (VotoCommento) sessione
                    .get(VotoCommento.class, new ChiavePrimaria(u.getId(), idEvento));
            sessione.delete(vc);
            tran.commit();
        } catch (HibernateException e) {
            if (tran != null) {
                tran.rollback();
            }
        } finally {
            sessione.close();
        }
    }
    
    /**
     * Metodo che elimina un evento
     *
     * @param factory
     * @param id id evento
     */
    public static void deleteEvento(SessionFactory factory, int id) {
        Session sessione = factory.openSession();
        Transaction tran = null;
        try {
            tran = sessione.beginTransaction();
            Evento e = (Evento) sessione.get(Evento.class, id);
            sessione.delete(e);
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
