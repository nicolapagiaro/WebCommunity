package pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe che rappresenta la relazione VOTO_COMMENTO nel db
 * @author nicola
 */
@Entity
@Table(name = "VOTO_COMMENTO")
public class VotoCommento implements Serializable {
    public final static int VOTO_0 = 0;
    public final static int VOTO_1 = 1;
    public final static int VOTO_2 = 2;
    public final static int VOTO_3 = 3;
    public final static int VOTO_4 = 4;
    public final static int VOTO_5 = 5;

    @EmbeddedId
    private ChiavePrimaria id;

    @Column(name = "commento")
    private String commento;

    @Column(name = "voto")
    private int voto;

    @ManyToOne
    @JoinColumn(name = "idUtente", insertable = false, updatable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "idEvento", insertable = false, updatable = false)
    private Evento evento;

    /**
     * Costruttore vuoto
     */
    public VotoCommento() {
    }

    /**
     * 
     * @param commento
     * @param voto
     * @param idUtente
     * @param idEvento
     */
    public VotoCommento(String commento, int voto, int idUtente, int idEvento) {
        id = new ChiavePrimaria(idUtente, idEvento);
        this.commento = commento;
        this.voto = voto;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * @return
     */
    public String getCommento() {
        return commento;
    }

    /**
     *
     * @param commento
     */
    public void setCommento(String commento) {
        this.commento = commento;
    }

    /**
     *
     * @return
     */
    public int getVoto() {
        return voto;
    }

    /**
     *
     * @param voto
     */
    public void setVoto(int voto) {
        if (voto >= 0 && voto <= 5) {
            this.voto = voto;
        }
    }

    /**
     * toString() method
     * @return 
     */
    @Override
    public String toString() {
        return "VotoCommento{" + "id=" + id + ", commento=" + commento + 
                ", voto=" + voto;
    }
}
