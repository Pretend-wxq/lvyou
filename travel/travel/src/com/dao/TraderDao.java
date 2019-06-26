package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Trader;

@Repository//注册spring管理bean
public class TraderDao extends BaseDao{

	//获取列表
	public List<Trader> getTraderList(int i, int size) {
		return getSession().createQuery("from Trader order by id desc", Trader.class)
				.setFirstResult(i).setMaxResults(size).list();
	}

	//根据id获取
	public Trader getTrader(int id) {
		return (Trader) getSession().get(Trader.class, id);
	}

	//根据用户名获取
	public Trader getTrader(String username) {
		return (Trader) getSession().createQuery("from Trader where username=:username").setParameter("username", username).uniqueResult();
	}
	
	//根据用户名和密码获取
	public Trader getTrader(String username, String password) {
		return (Trader) getSession().createQuery("from Trader where username=:username and password=:password").setParameter("username", username).setParameter("password", password).uniqueResult();
	}

	// 获取记录总数
	public int getTotal() {
		return Integer.parseInt(getSession().createQuery("select count(*) from Trader").uniqueResult().toString());
	}
}
