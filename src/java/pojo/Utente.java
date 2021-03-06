package pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe che rappresenta la relazione UTENTI nel db
 * @author FSEVERI\pagiaro3283
 */
@Entity
@Table(name = "UTENTI")
public class Utente implements Serializable{
   static final long serialVersionUID = 42L;
    
   @Id @GeneratedValue
   private int id;

   @Column(name = "nickname")
   private String nickname;
   
   @Column(name = "nome")
   private String nome;
   
   @Column(name = "cognome")
   private String cognome;
   
   @Column(name = "email")
   private String email;
   
   @OneToMany(mappedBy = "utente", fetch=FetchType.EAGER)
   private List<VotoCommento> votiCommenti;
   
   /**
    * Associazione molti a molti con categorie
    */
   @ManyToMany
   @JoinTable(
           name = "INTERESSE",
           joinColumns = {@JoinColumn(name = "idUtente")},
           inverseJoinColumns = {@JoinColumn(name = "idCategoria")}
   )
   private List<Categoria> categorie;
  
   
   /**
    * Costruttore vuoto
    */
   public Utente() {}

   /**
    * Costruttore parametrico #1
    * @param nickname nickname utente
    * @param nome nome utente
    * @param cognome cognome utente
    * @param email email utente
    */
    public Utente(String nickname, String nome, String cognome, String email) {
        this.nickname = nickname;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    /**
    * Costruttore parametrico #2
    * @param nickname nickname utente
    * @param nome nome utente
    * @param cognome cognome utente
    * @param email email utente
     * @param categorie lista delle categorie preferite
    */
    public Utente(String nickname, String nome, String cognome, String email,
            List<Categoria> categorie) {
        this(nickname, nome, cognome, email);
        this.categorie = categorie;
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
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     * @param nickname 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return 
     */
    public List<Categoria> getCategorie() {
        return categorie;
    }

    /**
     * 
     * @param categorie 
     */
    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }

    /**
     * 
     * @return 
     */
    public List<VotoCommento> getVotiCommenti() {
        return votiCommenti;
    }

    /**
     * 
     * @param votiCommenti 
     */
    public void setVotiCommenti(List<VotoCommento> votiCommenti) {
        this.votiCommenti = votiCommenti;
    }

    /**
     * Equals method
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
        final Utente other = (Utente) obj;
        return this.id == other.id;
    }

    /**
     * Hash code
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.nickname);
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.cognome);
        hash = 23 * hash + Objects.hashCode(this.email);
        return hash;
    }   
}
