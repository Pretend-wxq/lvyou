package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.User;

@Service	// 注解为spring管理bean
@Transactional	//配置此方法加入事务管理
public class UserService {

	@Resource
	private UserDao userDao;

	//通过用户名和密码获取
	public User getUser(String username, String password) {
		return userDao.getUser(username, password);
	}
	
	//通过用户名获取
	public User getUser(String username) {
		return userDao.getUser(username);
	}
	
	//获取所有
	public List<User> getUserList(int i, int size) {
		return userDao.getUserList(i, size);
	}

	//根据id获取
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	//更新
	public boolean update(User user) {
		return userDao.update(user);
	}

	//删除
	public boolean delete(User user) {
		return userDao.delete(user);
	}

	//添加
	public boolean add(User user) {
		return userDao.save(user);
	}

	//禁言
	public void forbid(int userid) {
		User user = userDao.getUser(userid);
		user.setStatus(1);
		userDao.update(user);
	}
	
	//解禁
	public void reforbid(int userid) {
		User user = userDao.getUser(userid);
		user.setStatus(0);
		userDao.update(user);
	}

	// 获取记录总数
	public int getTotal() {
		return userDao.getTotal();
	}
}
