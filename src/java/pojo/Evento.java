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
public class Evento implements Serializable {
    static final long serialVersionUID = 30L;
    
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dataE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataE;

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
    public Evento() {
    }

    /**
     * Costruttore parametrico
     * @param nome nome evento 
     * @param dataE data evento
     * @param via_numero via e numero civico evento
     * @param provincia povincia evento
     */
    public Evento(String nome, Date dataE, String via_numero, String provincia) {
        this.nome = nome;
        this.dataE = dataE;
        this.via_numero = via_numero;
        this.provincia = provincia;
    }

    
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
    public Date getDataE() {
        return dataE;
    }

    /**
     *
     * @param dataE
     */
    public void setDataE(Date dataE) {
        this.dataE = dataE;
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

    public List<Artista> getArtisti() {
        return artisti;
    }

    public void setArtisti(List<Artista> artisti) {
        this.artisti = artisti;
    }

    public List<VotoCommento> getVotiCommenti() {
        return votiCommenti;
    }

    public void setVotiCommenti(List<VotoCommento> votiCommenti) {
        this.votiCommenti = votiCommenti;
    }

    /**
     * Metodo che restituisce il voto medio dell'evento
     * @return voto medio dell'evento
     */
    public float getVotoMedio() {
        int temp = 0, count = 0;
        for (VotoCommento v : this.getVotiCommenti()) {
            temp += v.getVoto();
            count++;
        }
        if(count == 0) count = 1;
        return temp/count;
    }

    /**
     * Hashcode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        return this.id == other.id;
    }


    /**
     * toString()
     *
     * @return
     */
    @Override
    public String toString() {
        return "Evento{" + "id=" + id + ", nome=" + nome + ", data="
                + dataE + ", categoria=" + categoria + ", via_numero="
                + via_numero + ", provincia=" + provincia + '}';
    }

}
