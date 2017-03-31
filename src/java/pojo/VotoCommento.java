package pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
     * @return
     */
    public int getIdEvento() {
        return id.idEvento;
    }

    /**
     *
     * @param idEvento
     */
    public void setIdEvento(int idEvento) {
        id.idEvento = idEvento;
    }

    /**
     * @return
     */
    public int getIdUtente() {
        return id.idUtente;
    }

    /**
     * @param idUtente
     */
    public void setIdUtente(int idUtente) {
        id.idUtente = idUtente;
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
                ", voto=" + voto + ", utente=" + utente.getClass()
                + ", evento=" + evento.getClass() + '}';
    }
}

/**
 * Classe per la chiave primaria composta
 */
class ChiavePrimaria implements Serializable {

    @Column(name = "idUtente")
    protected int idUtente;

    @Column(name = "idEvento")
    protected int idEvento;

    /**
     * Costruttore vouto
     */
    public ChiavePrimaria() {
    }

    // getter e setter
    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * equals(obj) method
     *
     * @param obj oggetto
     * @return true/false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ChiavePrimaria)) {
            return false;
        }
        ChiavePrimaria temp = (ChiavePrimaria) obj;
        return (temp.idEvento == this.idEvento)
                && (temp.idUtente == this.idUtente);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * toString() method
     * @return 
     */
    @Override
    public String toString() {
        return "ChiavePrimaria{" + "idUtente=" + idUtente + ", idEvento=" + idEvento + '}';
    }
}
