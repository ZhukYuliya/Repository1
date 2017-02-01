package by.newnet.model;

/**
 * The Enum Role.
 */
public enum Role {
		

	ADMIN(1), OPERATOR(2), CUSTOMER(3);
	
	private int roleCoef;

	/**
	 * Instantiates a new role.
	 */
	Role() {
	}

	/**
	 * Instantiates a new role.
	 *
	 * @param roleCoef the role coef
	 */
	Role(int roleCoef) {
		this.roleCoef = roleCoef;
	}

	/**
	 * Gets the role coef.
	 *
	 * @return the role coef
	 */
	public int getRoleCoef() {
		return roleCoef;
	}

	/**
	 * Sets the role coef.
	 *
	 * @param roleCoef the new role coef
	 */
	// public?
	public void setRoleCoef(int roleCoef) {
		this.roleCoef = roleCoef;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return name();
	}
}
/*
 * private int id; private String name; //private List<Permission>
 * permissionList;
 * 
 * 
 * public int getId() { return id; } public void setId(int id) { this.id = id; }
 * public String getName() { return name; } public void setName(String name) {
 * this.name = name; } public List<Permission> getPermissionList() { return
 * permissionList; } public void setPermissionList(List<Permission>
 * permissionList) { this.permissionList = permissionList; }
 * 
 * @Override public int hashCode() { final int prime = 31; int result = 1;
 * result = prime * result + id; result = prime * result + ((name == null) ? 0 :
 * name.hashCode()); //result = prime * result + ((permissionList == null) ? 0 :
 * permissionList.hashCode()); return result; }
 * 
 * @Override public boolean equals(Object obj) { if (this == obj) return true;
 * if (obj == null) return false; if (getClass() != obj.getClass()) return
 * false; Role other = (Role) obj; if (id != other.id) return false; if (name ==
 * null) { if (other.name != null) return false; } else if
 * (!name.equals(other.name)) return false; if (permissionList == null) { if
 * (other.permissionList != null) return false; } else if
 * (!permissionList.equals(other.permissionList)) return false; return true; }
 * 
 * @Override public String toString() { return "Role [id=" + id + ", name=" +
 * name + ", permissionList=" + permissionList + "]"; }
 */
