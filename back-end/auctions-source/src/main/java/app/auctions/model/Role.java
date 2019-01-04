package app.auctions.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private long roleId;
	
	@Column(name="role_name",nullable=false,unique=true)
	private String roleName;

	@OneToMany(mappedBy="role",fetch=FetchType.LAZY)
    private Set<User> users;
	
	
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String role) {
		this.roleName = role;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) return false; 
		if (!this.getClass().equals(obj.getClass())) return false;
		Role obj2 = (Role)obj; 
		if((this.roleId == obj2.getRoleId()) && (this.roleName.equals(obj2.getRoleName())))
		{ return true; } 
		return false;
		}
	
	public int hashCode()
	{ 
		int tmp = 0; tmp = ( roleId + roleName ).hashCode();
	    return tmp; 
	}
}
