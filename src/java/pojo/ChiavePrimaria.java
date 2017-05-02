package pojo;

import java.io.Serializable;
import javax.persistence.Column;

/**
 * Classe per la chiave primaria composta
 */
public class ChiavePrimaria implements Serializable {

    @Column(name = "idUtente")
    protected int idUtente;

    @Column(name = "idEvento")
    protected int idEvento;

    /**
     * Costruttore vouto
     */
    public ChiavePrimaria() {
    }

    /**
     * 
     * @param idUtente
     * @param idEvento 
     */
    public ChiavePrimaria(int idUtente, int idEvento) {
        this.idUtente = idUtente;
        this.idEvento = idEvento;
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
