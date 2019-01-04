package app.auctions.service;

import javax.transaction.Transactional;

import app.auctions.dao.RoleDao;
import app.auctions.model.Role;

public class RoleServiceImpl implements RoleService{
	private RoleDao roleDao;
	 
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    
    @Transactional
	@Override
	public Role findRoleByName(String roleName) {
		return roleDao.findRoleByName(roleName);
	}

}
