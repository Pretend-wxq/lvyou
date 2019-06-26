package com.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GbookDao;
import com.entity.Gbook;

@Service	// 注解为spring管理bean
@Transactional	//配置此方法加入事务管理
public class GbookService {

	@Resource
	private GbookDao gbookDao;


	//获取列表
	public List<Gbook> getList(int i, int size) {
		return gbookDao.getList(i, size);
	}
	
	//获取所有用户留言
	public List<Gbook> getUserList(int i, int size) {
		return gbookDao.getUserList(i, size);
	}
	
	//获取所有商家留言
	public List<Gbook> getTraderList(int i, int size) {
		return gbookDao.getTraderList(i, size);
	}

	//根据id获取
	public Gbook getGbook(int id) {
		return gbookDao.getGbook(id);
	}

	//更新
	public boolean update(Gbook gbook) {
		gbook.setCreatetime(new Date());
		return gbookDao.update(gbook);
	}

	//删除
	public boolean delete(Gbook gbook) {
		return gbookDao.delete(gbook);
	}

	//添加
	public boolean add(Gbook gbook) {
		gbook.setCreatetime(new Date());
		return gbookDao.save(gbook);
	}

	// 获取记录总数
	public int getTotal() {
		return gbookDao.getTotal();
	}
	
	// 获取记录总数
	public int getUserTotal() {
		return gbookDao.getUserTotal();
	}
	
	// 获取记录总数
	public int getTraderTotal() {
		return gbookDao.getTraderTotal();
	}
	
}
