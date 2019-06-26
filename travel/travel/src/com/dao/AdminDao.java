package com.dao;

import org.springframework.stereotype.Repository;

import com.entity.Admin;

@Repository//注册spring管理bean
public class AdminDao extends BaseDao{

	//根据用户名和密码查找用户
	public Admin getAdmin(String username, String password) {
		return (Admin) getSession().createQuery("from Admin where username=:username and password=:password")
				.setParameter("username", username).setParameter("password", password).uniqueResult();
	}
	
	
}
