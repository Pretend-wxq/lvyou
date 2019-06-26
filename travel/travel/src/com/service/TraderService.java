package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TraderDao;
import com.entity.Trader;

@Service	// 注解为spring管理bean
@Transactional	//配置此方法加入事务管理
public class TraderService {

	@Resource
	private TraderDao traderDao;


	//获取所有
	public List<Trader> getTraderList(int i, int size) {
		return traderDao.getTraderList(i, size);
	}

	// 通过用户名获取
	public Trader getTrader(String username) {
		return traderDao.getTrader(username);
	}
	
	// 通过用户名和密码获取
	public Trader getTrader(String username, String password) {
		return traderDao.getTrader(username,password);
	}
	
	//根据id获取
	public Trader getTrader(int id) {
		return traderDao.getTrader(id);
	}

	//更新
	public boolean update(Trader trader) {
		return traderDao.update(trader);
	}

	//删除
	public boolean delete(Trader trader) {
		return traderDao.delete(trader);
	}

	//添加
	public boolean add(Trader trader) {
		return traderDao.save(trader);
	}

	//禁言
	public void forbid(int traderid) {
		Trader trader = traderDao.getTrader(traderid);
		trader.setStatus(1);
		traderDao.update(trader);
	}
	
	//解禁
	public void reforbid(int traderid) {
		Trader trader = traderDao.getTrader(traderid);
		trader.setStatus(0);
		traderDao.update(trader);
	}

	// 审核
	public void check(int traderid) {
		Trader trader = traderDao.getTrader(traderid);
		trader.setStatus(0);
		traderDao.update(trader);
	}

	// 获取记录总数
	public int getTotal() {
		return traderDao.getTotal();
	}
}
