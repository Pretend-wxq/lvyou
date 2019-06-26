package com.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.NewsDao;
import com.entity.News;

@Service	// 注解为spring管理bean
@Transactional	//配置此方法加入事务管理
public class NewsService {

	@Resource
	private NewsDao newsDao;


	//获取所有
	public List<News> getNewsList(int i, int size) {
		return newsDao.getNewsList(i, size);
	}

	//根据id获取
	public News getNews(int id) {
		return newsDao.getNews(id);
	}

	//更新
	public boolean update(News news) {
		news.setCreatetime(new Date());
		return newsDao.update(news);
	}

	//删除
	public boolean delete(News news) {
		return newsDao.delete(news);
	}

	//添加
	public boolean add(News news) {
		news.setCreatetime(new Date());
		return newsDao.save(news);
	}

	//我的优惠
	public List<News> getNewsSelf(int traderid, int i, int size) {
		return newsDao.getNewsList(traderid, i, size);
	}

	//获取首页需要数据
	public List<News> getIndexList(int count) {
		return newsDao.getIndexList(count);
	}

	// 获取记录总数
	public int getTotal() {
		return newsDao.getTotal();
	}
	
	// 获取记录总数
	public int getTotalSelf(int traderid) {
		return newsDao.getTotalSelf(traderid);
	}
}
