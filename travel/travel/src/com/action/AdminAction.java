package com.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.service.AdminService;

@Namespace("/admin")
@Results({
	@Result(name="login",location="/admin/login.jsp"),
	@Result(name="main",location="/admin/main.jsp"),
})
@SuppressWarnings("serial")
public class AdminAction extends BaseAction{
	
	private String username;
	private String password;
	
	@Resource
	private AdminService adminService;
	
    
	@Action("login")
	public String login(){
		Admin admin = adminService.getAdmin(username, password);
		if (admin == null) {
			addActionError("用户名或密码错误");
			return "login";
		}else {
			ActionContext.getContext().getSession().put("user", admin);
			return "main";
		}
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
