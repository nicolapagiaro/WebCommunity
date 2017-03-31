package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Classe che rappresenta la relazione EVENTI nel db
 * @author nicola
 */
@Entity
@Table(name = "EVENTI")
public class Evento implements Serializable{
    static final long serialVersionUID = 30L;
    
    @Id @GeneratedValue
    private int id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "dataE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data; 
    
    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;
    
    @Column(name = "via_numero")
    private String via_numero;
    
    @Column(name = "provincia")
    private String provincia;
    
    @ManyToMany(mappedBy = "eventi")
    private List<Artista> artisti;
    
    @OneToMany(mappedBy = "evento")
    private List<VotoCommento> votiCommenti;

    /**
     * Costruttore vuoto
     */
    public Evento() {}

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
    public Date getData() {
        return data;
    }

    /**
     * 
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * 
     * @return 
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * 
     * @param categoria 
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * 
     * @return 
     */
    public String getVia_numero() {
        return via_numero;
    }

    /**
     * 
     * @param via_numero 
     */
    public void setVia_numero(String via_numero) {
        this.via_numero = via_numero;
    }

    /**
     * 
     * @return 
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * 
     * @param provincia 
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * toString()
     * @return 
     */
    @Override
    public String toString() {
        return "Evento{" + "id=" + id + ", nome=" + nome + ", data=" 
                + data + ", categoria=" + categoria + ", via_numero=" 
                + via_numero + ", provincia=" + provincia + '}';
    }
    
}
