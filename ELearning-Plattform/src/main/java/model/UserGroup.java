package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_groups database table.
 * 
 */
@Entity
@Table(name="user_groups")
@NamedQueries({
	@NamedQuery(name="UserGroup.findGroupById", query="SELECT u FROM UserGroup u WHERE u.email = :email"),
	@NamedQuery(name="UserGroup.findAll", query="SELECT u FROM UserGroup u")
})
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String groupname;

	public UserGroup() {
	}
	
    public UserGroup(String email, String groupname) {
        this.email = email;
        this.groupname = groupname;
    }

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}