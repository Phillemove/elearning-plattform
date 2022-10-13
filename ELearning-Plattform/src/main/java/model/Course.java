
package model;

import java.io.Serializable;

public class Course implements Serializable {
	private static final long serialVersionUID = 123412341234L;
	
	private String courseName;
	private String description;
	private String thema;
	private int price;

	
	public Course() {}

	public Course(String courseName, String description, String thema, int price) {
		this.courseName = courseName;
		this.description = description;
		this.thema = thema;
		this.price = price;
	}

		public String getCourseName() {
			return this.courseName;
		}
		
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		
		public String getDescription() {
			return this.description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		public String getThema() {
			return this.thema;
		}
		
		public void setThema(String thema) {
			this.thema = thema;
		}
		
		public int getPrice() {
			return this.price;
		}
		
		public void setPrice(int price) {
			this.price = price;
		}
}
