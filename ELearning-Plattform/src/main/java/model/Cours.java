package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the courses database table.
 * 
 */
@Entity
@Table(name="courses")
@NamedQueries({
@NamedQuery(name="Cours.findAll", query="SELECT c FROM Cours c"),
@NamedQuery(name="Cours.findByID", query="SELECT c FROM Cours c WHERE c.courseid = :courseid"),
@NamedQuery(name="Cours.findByTeacher", query="SELECT c FROM Cours c WHERE c.teacher = :teacher")
})

public class Cours implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long courseid;

	private String coursename;

	private String description;

	private double price;

	private String teacher;

	private String thema;

	@Override
	public String toString() {
		return "Cours [courseid=" + courseid + ", coursename=" + coursename + ", description=" + description
				+ ", price=" + price + ", teacher=" + teacher + ", thema=" + thema + ", markedcourses=" + markedcourses
				+ ", mycourses=" + mycourses + "]";
	}

	//bi-directional many-to-one association to Markedcours
	@OneToMany(mappedBy="cours")
	private List<Markedcours> markedcourses;

	//bi-directional many-to-one association to Mycours
	@OneToMany(mappedBy="cours")
	private List<Mycours> mycourses;

	public Cours() {
	}

	public Long getCourseid() {
		return this.courseid;
	}

	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}

	public String getCoursename() {
		return this.coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTeacher() {
		return this.teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getThema() {
		return this.thema;
	}

	public void setThema(String thema) {
		this.thema = thema;
	}

	public List<Markedcours> getMarkedcourses() {
		return this.markedcourses;
	}

	public void setMarkedcourses(List<Markedcours> markedcourses) {
		this.markedcourses = markedcourses;
	}

	public Markedcours addMarkedcours(Markedcours markedcours) {
		getMarkedcourses().add(markedcours);
		markedcours.setCours(this);

		return markedcours;
	}

	public Markedcours removeMarkedcours(Markedcours markedcours) {
		getMarkedcourses().remove(markedcours);
		markedcours.setCours(null);

		return markedcours;
	}

	public List<Mycours> getMycourses() {
		return this.mycourses;
	}

	public void setMycourses(List<Mycours> mycourses) {
		this.mycourses = mycourses;
	}

	public Mycours addMycours(Mycours mycours) {
		getMycourses().add(mycours);
		mycours.setCours(this);

		return mycours;
	}

	public Mycours removeMycours(Mycours mycours) {
		getMycourses().remove(mycours);
		mycours.setCours(null);

		return mycours;
	}

}