package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.User;
import com.service.UserService;

@Namespace("/admin")
@Results({
	@Result(name="list",location="/admin/pages/user-list.jsp"),
	@Result(name="add",location="/admin/pages/user-add.jsp"),
	@Result(name="update",location="/admin/pages/user-update.jsp"),
})
@SuppressWarnings("serial")
public class UserAction extends BaseAction{
	
	private User user;
	private List<User> users;
	@Resource
	private UserService userService;
	
	
	//获取列表
	@Action("userList")
	public String list(){
		users = userService.getUserList((page-1)*size, size);
		pages = getPages(userService.getTotal(), size);
		return "list";
	}

	//添加
	@Action("userAdd")
	public String add(){
		if (userService.getUser(user.getUsername()) == null) {
			userService.add(user);
			return list();
		} else {
			addActionError("用户名已存在");
			return "add";
		}
	}	
	
	//更新页面
	@Action("userUpdatePage")
	public String updatePage(){
		user = userService.getUser(user.getId());
		return "update";
	}
	
	//更新
	@Action("userUpdate")
	public String update(){
		userService.update(user);
		return list();
	}
	
	//删除
	@Action("userDelete")
	public String delete(){
		userService.delete(user);
		return list();
	}

	//禁言
	@Action("userForbid")
	public String forbid(){
		userService.forbid(user.getId());
		return list();
	}
	
	//解禁
	@Action("userReforbid")
	public String reforbid(){
		userService.reforbid(user.getId());
		return list();
	}
	

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
