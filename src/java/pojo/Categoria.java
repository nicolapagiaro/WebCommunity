package pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe che rappresenta la relazione CATEGORIE nel db
 * @author nicola
 */
@Entity
@Table(name = "CATEGORIE")
class Categoria implements Serializable{
       static final long serialVersionUID = 32L;
}
