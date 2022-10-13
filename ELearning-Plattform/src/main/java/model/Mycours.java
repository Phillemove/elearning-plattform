package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mycourses database table.
 * 
 */
@Entity
@Table(name="mycourses")
@NamedQueries({
@NamedQuery(name="Mycours.findAll", query="SELECT m FROM Mycours m"),
@NamedQuery(name="Mycours.findMy", query="SELECT m FROM Mycours m WHERE m.email = :email"),
@NamedQuery(name="Mycours.findByID", query="SELECT m FROM Mycours m WHERE m.cours= :cours and m.email = :email"),
@NamedQuery(name="Mycours.done", query="UPDATE Mycours m SET m.isdone = 'true'  WHERE m.cours= :cours and m.email = :email"),
@NamedQuery(name="Mycours.undone", query="UPDATE Mycours m SET m.isdone='false' WHERE m.cours= :cours and m.email = :email"),
@NamedQuery(name="Mycours.findReady", query="SELECT m.cours FROM Mycours m WHERE m.isdone= true and m.email = :email")
})

public class Mycours implements Serializable {
	private static final long serialVersionUID = 2L;

	@Id
	private String email;

	private String date;

	private Boolean isdone;

	//bi-directional many-to-one association to Cours
	@ManyToOne
	@JoinColumn(name="courseid")
	private Cours cours;

	public Mycours() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Boolean getIsdone() {
		return this.isdone;
	}

	public void setIsdone(Boolean isdone) {
		this.isdone = isdone;
	}

	public Cours getCours() {
		return this.cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

}