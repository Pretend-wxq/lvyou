package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.News;

@Repository//注册spring管理bean
public class NewsDao extends BaseDao{

	//获取列表
	public List<News> getNewsList(int i, int size) {
		return getSession().createQuery("from News order by id desc", News.class)
				.setFirstResult(i).setMaxResults(size).list();
	}

	//根据id获取
	public News getNews(int id) {
		return (News) getSession().get(News.class, id);
	}

	//我的优惠
	public List<News> getNewsList(int traderid, int i, int size) {
		return getSession().createQuery("from News where trader_id=:tarderid  order by id desc", News.class)
				.setParameter("tarderid", traderid).setFirstResult(i).setMaxResults(size).list();
	}

	//获取首页需要数据
	public List<News> getIndexList(int count) {
		return getSession().createQuery("from News order by id desc", News.class).setMaxResults(count).list();
	}

	// 获取记录总数
	public int getTotal() {
		return Integer.parseInt(getSession().createQuery("select count(*) from News").uniqueResult().toString());
	}

	// 获取记录总数
	public int getTotalSelf(int traderid) {
		return Integer.parseInt(getSession().createQuery("select count(*) from News where trader_id=:tarderid")
				.setParameter("tarderid", traderid).uniqueResult().toString());
	}
}
