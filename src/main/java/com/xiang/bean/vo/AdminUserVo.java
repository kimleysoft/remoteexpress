package com.xiang.bean.vo;
import java.util.List;
import java.util.Set;
public class AdminUserVo extends BaseVo{

    private String userName;
    private String nick;
    private String roles;
    private String permission;
	private List<String> menus;
	private Set<String> perms;
	private List<String> houseIds;
	private List<String> workshopIds;
	private List<Long> roleIds;
	private List<String> serviceIds;
	private List<String> modules;
	private boolean admin;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public List<String> getMenus() {
		return menus;
	}
	public void setMenus(List<String> menus) {
		this.menus = menus;
	}
	public Set<String> getPerms() {
		return perms;
	}
	public void setPerms(Set<String> perms) {
		this.perms = perms;
	}
	public List<String> getHouseIds() {
		return houseIds;
	}
	public void setHouseIds(List<String> houseIds) {
		this.houseIds = houseIds;
	}
	public List<String> getWorkshopIds() {
		return workshopIds;
	}
	public void setWorkshopIds(List<String> workshopIds) {
		this.workshopIds = workshopIds;
	}
	public List<Long> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
	public List<String> getServiceIds() {
		return serviceIds;
	}
	public void setServiceIds(List<String> serviceIds) {
		this.serviceIds = serviceIds;
	}
	public List<String> getModules() {
		return modules;
	}
	public void setModules(List<String> modules) {
		this.modules = modules;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
