package pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
}
