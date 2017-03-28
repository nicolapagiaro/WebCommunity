package pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    
}
