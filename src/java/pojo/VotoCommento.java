package pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe che rappresenta la relazione VOTO_COMMENTO nel db
 * @author nicola
 */
@Entity
@Table(name = "VOTO_COMMENTO")
public class VotoCommento implements Serializable{
    public final static int VOTO_0 = 0;
    public final static int VOTO_1 = 1;
    public final static int VOTO_2 = 2;
    public final static int VOTO_3 = 3;
    public final static int VOTO_4 = 4;
    public final static int VOTO_5 = 5;

    /**
     * Classe per la chiave primaria composta
     */
    class ChiavePrimaria {
        @Column(name = "idUtente")
        private int idUtente;
        
        @Column(name = "idEvento")
        private int idEvento;

        /**
         * Costruttore vouto
         */
        public ChiavePrimaria() {}

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
    }
    
    @EmbeddedId
    private ChiavePrimaria id;  
    
    @Column(name = "commento")
    private String commento;
    
    @Column(name = "voto")
    private int voto;
    
    /**
     * Costruttore vuoto
     */
    public VotoCommento() {
        id = new ChiavePrimaria();
    }

    /**
     * 
     * @return 
     */
    public ChiavePrimaria getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(ChiavePrimaria id) {
        this.id = id;
    }

    /**
     * 
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
        if(voto >= 0 && voto <= 5)
            this.voto = voto;
    }
}
