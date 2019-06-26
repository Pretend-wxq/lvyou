package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Place;

@Repository//注册spring管理bean
public class PlaceDao extends BaseDao{

	//获取列表
	public List<Place> getPlaceList(int i, int size) {
		return getSession().createQuery("from Place order by id desc", Place.class)
				.setFirstResult(i).setMaxResults(size).list();
	}

	//根据id获取
	public Place getPlace(int id) {
		return (Place) getSession().get(Place.class, id);
	}

	//获取首页需要数据
	public List<Place> getIndexList(int count) {
		return getSession().createQuery("from Place order by id desc", Place.class).setMaxResults(count).list();
	}

	// 获取记录总数
	public int getTotal() {
		return Integer.parseInt(getSession().createQuery("select count(*) from Place").uniqueResult().toString());
	}
	
}
