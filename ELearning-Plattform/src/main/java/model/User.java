package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findUserById", query="SELECT u FROM User u WHERE u.email = :email"),
	@NamedQuery(name="User.findAllUsers", query="SELECT u FROM User u")
})
public class User implements Serializable {
	

	private static final long serialVersionUID = 6242718990854140429L;

	@Id
	private String email;

	private String name;

	private String password;

	public User() {
	}
	
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}