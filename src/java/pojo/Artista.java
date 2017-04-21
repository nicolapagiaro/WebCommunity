package pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Classe che rappresenta la relazione ARTISTI nel db
 * @author nicola
 */
@Entity
@Table(name = "ARTISTI")
public class Artista implements Serializable{
    static final long serialVersionUID = 55L;
    
    @Id @GeneratedValue
    private int id;
    
    @Column(name = "nome")
    private String nome;
   
    @Column(name = "cognome")
    private String cognome;
    
    /**
     * Associazione molti a molti fra artisti e gli eventi a cui partecipano
     */
    @ManyToMany
    @JoinTable(
            name = "PARTECIPAZIONE",
            joinColumns = {@JoinColumn(name = "idArtista")},
            inverseJoinColumns = {@JoinColumn(name = "idEvento")}
    )
    private List<Evento> eventi;

    /**
     * Costruttore vuoto
     */
    public Artista() {}

    /**
     * 
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return 
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * 
     * @return 
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * 
     * @param cognome 
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * 
     * @return 
     */
    public List<Evento> getEventi() {
        return eventi;
    }

    /**
     * 
     * @param eventi 
     */
    public void setEventi(List<Evento> eventi) {
        this.eventi = eventi;
    }
}
