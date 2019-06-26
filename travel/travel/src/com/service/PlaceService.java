package com.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PlaceDao;
import com.entity.Place;

@Service	// 注解为spring管理bean
@Transactional	//配置此方法加入事务管理
public class PlaceService {

	@Resource
	private PlaceDao placeDao;


	//获取所有
	public List<Place> getPlaceList(int i, int size) {
		return placeDao.getPlaceList(i, size);
	}

	//根据id获取
	public Place getPlace(int id) {
		return placeDao.getPlace(id);
	}

	//更新
	public boolean update(Place news) {
		news.setCreatetime(new Date());
		return placeDao.update(news);
	}

	//删除
	public boolean delete(Place news) {
		return placeDao.delete(news);
	}

	//添加
	public boolean add(Place news) {
		news.setCreatetime(new Date());
		return placeDao.save(news);
	}

	//获取首页需要数据
	public List<Place> getIndexList(int count) {
		return placeDao.getIndexList(count);
	}

	// 获取记录总数
	public int getTotal() {
		return placeDao.getTotal();
	}

}
