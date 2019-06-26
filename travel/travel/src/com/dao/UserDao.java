package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository//注册spring管理bean
public class UserDao extends BaseDao{


	//根据用户名和密码查找用户
	public User getUser(String username, String password) {
		return (User) getSession().createQuery("from User where username=:username and password=:password")
				.setParameter("username", username).setParameter("password", password).uniqueResult();
	}
	
	//根据用户名查找用户
	public User getUser(String username) {
		return (User) getSession().createQuery("from User where username=:username")
				.setParameter("username", username).uniqueResult();
	}

	//获取所有用户信息
	public List<User> getUserList(int i, int size) {
		return getSession().createQuery("from User  order by id desc", User.class)
				.setFirstResult(i).setMaxResults(size).list();
	}

	//根据id获取用户信息
	public User getUser(int id) {
		return (User) getSession().get(User.class, id);
	}

	// 获取记录总数
	public int getTotal() {
		return Integer.parseInt(getSession().createQuery("select count(*) from User").uniqueResult().toString());
	}
}
