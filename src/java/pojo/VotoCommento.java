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
    class ChiavePrimaria implements Serializable{
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

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }  
    }
    
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
    public VotoCommento() {}

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
