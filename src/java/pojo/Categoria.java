package pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe che rappresenta la relazione CATEGORIE nel db
 * @author nicola
 */
@Entity
@Table(name = "CATEGORIE")
class Categoria implements Serializable{
    static final long serialVersionUID = 32L;
    
    @Id @GeneratedValue
    private int id;
    
    @Column(name = "nome")
    private String nome;
    
    @ManyToMany(mappedBy = "categorie")
    private List<Utente> utenti;
    
    @OneToMany(mappedBy = "categoria")
    private List<Evento> eventi;

    /**
     * Costruttore vuoto
     */
    public Categoria() {}

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
    public List<Utente> getUtenti() {
        return utenti;
    }

    /**
     * 
     * @param utenti 
     */
    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
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
