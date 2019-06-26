package com.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Trader;
import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.service.TraderService;
import com.service.UserService;

@Namespace("/index")
@Results({
	@Result(name="login",location="/index/login.jsp"),
	@Result(name="register",location="/index/register.jsp"),
	@Result(name="userSave",location="/index/self.jsp"),
	@Result(name="reindex", type="redirect", location="index.action"),
})
@SuppressWarnings("serial")
public class AuthAction extends BaseAction{
	
	private String username;
	private String password;
	private String phone;
	private String nickname;
	private String company;
	private int type;
	private User user;
	private Trader trader;
	@Resource
	private UserService userService;
	@Resource
	private TraderService traderService;
	
	
	// 用户登录
	@Action("login")
	public String login(){
		switch (type) {
		case 1:
			User user = userService.getUser(username, password);
			if (user == null) {
				addActionMessage("用户名或密码错误");
				return "login";
			}else {
				ActionContext.getContext().getSession().put("user", user);
				ActionContext.getContext().getSession().put("type", type);
				return "reindex";
			}
		case 2:
			Trader trader = traderService.getTrader(username, password);
			if (trader == null) {
				addActionError("用户名或密码错误");
				return "login";
			}else if (trader.getStatus()==2) {
				addActionError("该用户还未通过审核");
				return "login";
			}else {
				ActionContext.getContext().getSession().put("user", trader);
				ActionContext.getContext().getSession().put("type", type);
				return "reindex";
			}
		}
		return null;
	}

	// 用户注册
	@Action("register")
	public String register(){
		switch (type) {
		case 1:
			if(username==null || username.trim().equals("") || 
				password==null || password.trim().equals("")){
				addActionMessage("用户名和密码不可为空");
				return "register";
			}else if (userService.getUser(username) == null) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setNickname(nickname);
				user.setPhone(phone);
				userService.add(user);
				addActionMessage("注册成功, 请登录");
				return "login";
			}else {
				addActionMessage("用户名已存在");
				return "register";
			}
		case 2:
			if(username.equals("") || password.equals("")){
				addActionError("用户名和密码不可为空");
				return "register";
			}else if (traderService.getTrader(username) == null) {
				Trader trader = new Trader();
				trader.setUsername(username);
				trader.setPassword(password);
				trader.setCompany(company);
				trader.setPhone(phone);
				trader.setStatus(2);
				traderService.add(trader);
				addActionError("注册成功, 请登录");
				return "login";
			}else {
				addActionError("用户名已存在");
				return "register";
			}
		}
		return null;
	}
	
	/**
	 * 用户注销
	 * @return
	 */
	@Action("logout")
	public String logout(){
		ActionContext.getContext().getSession().remove("user");
		ActionContext.getContext().getSession().remove("type");
		return "login";
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	@Action("saveinfo")
	public String userSave(){
		if(type == 1){
			userService.update(user);
			ActionContext.getContext().getSession().put("user", user);
		}else if (type == 2) {
			traderService.update(trader);
			ActionContext.getContext().getSession().put("user", trader);
		}
		addActionMessage("修改成功");
		return "userSave";
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Trader getTrader() {
		return trader;
	}
	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	
}
