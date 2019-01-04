package app.auctions.dao;

import app.auctions.model.Category;
import app.auctions.model.Role;

public interface RoleDao {
	 public Role findRoleByName(String roleName);
}
