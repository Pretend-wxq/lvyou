package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Gbook;

@Repository//注册spring管理bean
public class GbookDao extends BaseDao{

	//获取列表
	public List<Gbook> getList(int i, int size) {
		return getSession().createQuery("from Gbook order by id desc", Gbook.class)
				.setFirstResult(i).setMaxResults(size).list();
	}
	
	//获取列表
	public List<Gbook> getUserList(int i, int size) {
		return getSession().createQuery("from Gbook where type=1 order by id desc", Gbook.class)
				.setFirstResult(i).setMaxResults(size).list();
	}
	
	//获取列表
	public List<Gbook> getTraderList(int i, int size) {
		return getSession().createQuery("from Gbook where type=2 order by id desc", Gbook.class)
				.setFirstResult(i).setMaxResults(size).list();
	}

	//根据id获取
	public Gbook getGbook(int id) {
		return (Gbook) getSession().get(Gbook.class, id);
	}

	// 获取记录总数
	public int getTotal() {
		return Integer.parseInt(getSession().createQuery("select count(*) from Gbook").uniqueResult().toString());
	}
	
	// 获取记录总数
	public int getUserTotal() {
		return Integer.parseInt(getSession().createQuery("select count(*) from Gbook where type=1").uniqueResult().toString());
	}
	
	// 获取记录总数
	public int getTraderTotal() {
		return Integer.parseInt(getSession().createQuery("select count(*) from Gbook where type=2").uniqueResult().toString());
	}
	
}
