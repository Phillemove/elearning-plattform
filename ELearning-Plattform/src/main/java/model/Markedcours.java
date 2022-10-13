package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the markedcourses database table.
 * 
 */
@Entity
@Table(name="markedcourses")
@NamedQueries({
@NamedQuery(name="Markedcours.findAll", query="SELECT m FROM Markedcours m"),
@NamedQuery(name="Markedcours.findMy", query="SELECT m.cours FROM Markedcours m WHERE m.email = :email"),
@NamedQuery(name="Markedcours.findmark", query="SELECT m FROM Markedcours m WHERE m.email = :email AND m.cours = :cours"),
@NamedQuery(name="Markedcours.unmark", query="DELETE FROM Markedcours m WHERE m.email = :email AND m.cours = :cours")
})

public class Markedcours implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	//bi-directional many-to-one association to Cours
	@ManyToOne
	@JoinColumn(name="courseid")
	private Cours cours;

	public Markedcours() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cours getCours() {
		return this.cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

}