package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

@Stateless
public class DBManager {

	@PersistenceContext(unitName="ELearning-Plattform")
	private EntityManager manager;
	
	private Connection c = null;
	private Statement stmt = null;
	

	public DBManager() {
	}
	
	public boolean createCourse(Cours course) {
		boolean result = false;
		manager.persist(course);
		Cours cours = getCours(course.getCourseid());;
		if(cours!=null) {
			result =true;
		}
		return result;
	}

	// TODO: in die Tabelle MyCourses einen neuen Eintrag legen.
	// TODO: das Attribute isDone ist false am Anfang
	// TODO: nutze die unten definierte Methode getDate, um das Datum zu speichern.
	// (Wann wurde den Kurs eingekauft/ in den Kurs eingeschrieben)
	public boolean myCourse(String courseID, String studentEmail) {
		boolean success = false;
		Mycours temp = new Mycours();
		Cours thisCourse =getCours(toLong(courseID));
		temp.setCours(thisCourse);
		temp.setDate(getDate());
		temp.setEmail(studentEmail);
		temp.setIsdone(false);
		manager.persist(temp);
		Mycours cours = getMyCourse(thisCourse,studentEmail);;
		if(cours!=null) {
			success =true;
		}
		
		
		return success;
	}

	// TODO: zur�ckliefen alle gekaufte Kurse aus dem DB, Je nach StudentEmail
	public List<Mycours> getMyCourses(String studentEmail) {
		List<Mycours> result = null;
		System.out.println("getmycourses: " + studentEmail);
		TypedQuery<Mycours> query = manager.createNamedQuery("Mycours.findMy", Mycours.class);
		query.setParameter("email", studentEmail);
		try {
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Cours> getAllCourses() {
		TypedQuery<Cours> query = manager.createNamedQuery("Cours.findAll", Cours.class);
		List<Cours> result = null;
		try {
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// TODO: aus der Tabelle Courses hole alle Kurse nach der eingegebenen Email.
	public List<Cours> getFromMeCreatedCourses(String teacherEmail) {
		List<Cours> result = new ArrayList();
		TypedQuery<Cours> query = manager.createNamedQuery("Cours.findByTeacher", Cours.class);
		query.setParameter("teacher",teacherEmail);
		try {
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// TODO: die Tablle markedCourses, liefere alle Kurse nach der Email zur�ck
	public List<Cours> getFromMeMarkedCourses(String email) {
		List<Cours> result = new ArrayList();
		TypedQuery<Cours> query = manager.createNamedQuery("Markedcours.findMy", Cours.class);
		query.setParameter("email",email);
		try {
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean buyCourse(String email, String courseID) {
		//boolean success = false;
		System.out.println("DBManager buyCourse");
		Mycours mc = new Mycours();
		mc.setCours(getCours(toLong(courseID)));
		mc.setDate((new Date()).toString());
		mc.setEmail(email);
		mc.setIsdone(false);
		manager.persist(mc);
		return true;
	}
	
	public boolean unBuyCourse(String email, String courseID) {
		System.out.println("DBManager unBuyCourse");
		Cours course = getCours(toLong(courseID));
		TypedQuery<Mycours> query = manager.createNamedQuery("Mycours.findByID", Mycours.class);
		query.setParameter("cours", course);
		query.setParameter("email", email);
		Mycours cours = null;
		try {
			cours = query.getSingleResult();		
			manager.remove(cours);
			System.out.println("removed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DBManager unBuyCourse wird verlassen");
		return true;
	}

	// TODO: erstelle einen neuen Eintrag in der Tablle MarkedCourses!
	public boolean markCourse(String email, String courseID) {
		boolean success = false;
		Markedcours temp = new Markedcours();
		Cours thisCourse =getCours(toLong(courseID));
		temp.setCours(thisCourse);		
		temp.setEmail(email);
		manager.persist(temp);
		return true;
	}

	// TODO: delete marked Course from MarkedCoureses table !
	public boolean unmarkCourse(String email, String courseid) {
		boolean success = false;
		Cours course=getCours(toLong(courseid));
		TypedQuery<Markedcours> query =manager.createNamedQuery("Markedcours.findmark",Markedcours.class);
		query.setParameter("cours", course);
		query.setParameter("email", email);
		Markedcours cours = null;
		try {
			cours = query.getSingleResult();
			manager.remove(cours);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(cours != null) {
			success =true;
		}
		return success;
	}

	// TODO: in der Tablle myCourses suche nach dem studentId und den Kurs und mache
	// denKurse Done = true ==> der Kurse ist fertig.
	public boolean makeCourseDone(String email, String courseid) {
		boolean success = false;	
		Cours course=getCours(toLong(courseid));
		TypedQuery<Mycours> query =manager.createNamedQuery("Mycours.findByID",Mycours.class);
		query.setParameter("cours", course);
		query.setParameter("email", email);
		Mycours cours = null;
		try {
			cours = query.getSingleResult();
			cours.setIsdone(true);
			manager.merge(cours);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(cours.getIsdone()) {
			success =true;
		}
		
		return success;
	}

	// TODO: in der Tablle myCourses suche nach dem studentId und den Kurs und mache
	// denKurse Done = false ==> der Kurse ist noch nicht fertig.
	public boolean makeCourseStillNotDone(String email, String courseid) {
		boolean success = false;
		Cours course=getCours(toLong(courseid));
		//manager.createNamedQuery("Mycours.undone", Mycours.class).setParameter("email",email).setParameter("cours", course);
		TypedQuery<Mycours> query =manager.createNamedQuery("Mycours.findByID",Mycours.class);
		query.setParameter("cours", course);
		query.setParameter("email", email);
		Mycours cours = null;
		try {
			cours = query.getSingleResult();
			cours.setIsdone(false);
			manager.merge(cours);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!cours.getIsdone()) {
			success =true;
		}
		return success;
	}

	// TODO: in der Table myCourses nach der Email liefere alle Kurse zur�ck, dessen
	// isDone
	// = true ist.
	public List<Cours> getMyFinshedCourses(String email) {
		List<Cours> result = new ArrayList();
		manager.createNamedQuery("Mycours.done", Mycours.class).setParameter("email",email);
		TypedQuery<Cours> query =manager.createNamedQuery("Mycours.findReady",Cours.class);
		query.setParameter("email", email);
		try {
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// TODO: in der Tabelle myCourses hole alle myCourses und berechne den
	// tatalPrice
	public double getMyTotalPrice(String email) {
		double result = 0.0;
		List<Mycours> Courses = new ArrayList();
		TypedQuery<Mycours> query =manager.createNamedQuery("Mycours.findMy",Mycours.class);
		query.setParameter("email", email);	
		try {
			Courses = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Mycours mycours : Courses) {
			result+=mycours.getCours().getPrice();
		}
		
		return result;
	}

	private String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	private Long toLong(String toConvert) {
		
		return Long.valueOf(toConvert).longValue();
	}
	private Cours getCours(long courseid) {
		System.out.println("getCours in DBManager with courseid: " + courseid);
		TypedQuery<Cours> query =manager.createNamedQuery("Cours.findByID",Cours.class);
		query.setParameter("courseid", courseid);
		Cours cours = null;
		try {
			cours = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cours;
	}
	private Mycours getMyCourse(Cours course,String email) {
		TypedQuery<Mycours> query =manager.createNamedQuery("Mycours.findByID",Mycours.class);
		query.setParameter("cours", course);
		query.setParameter("email", email);
		Mycours cours = null;
		try {
			cours = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cours;
	}
	
}
