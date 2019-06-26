package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Trader;
import com.service.TraderService;

@Namespace("/admin")
@Results({
	@Result(name="list",location="/admin/pages/trader-list.jsp"),
	@Result(name="add",location="/admin/pages/trader-add.jsp"),
	@Result(name="update",location="/admin/pages/trader-update.jsp"),
})
@SuppressWarnings("serial")
public class TraderAction extends BaseAction{
	
	private Trader trader;
	private List<Trader> traders;
	@Resource
	private TraderService traderService;
	
	
	//获取列表
	@Action("traderList")
	public String list(){
		traders = traderService.getTraderList((page-1)*size, size);
		pages = getPages(traderService.getTotal(), size);
		return "list";
	}
	
	//添加
	@Action("traderAdd")
	public String add(){
		if (traderService.getTrader(trader.getUsername()) == null) {
			traderService.add(trader);
			return list();
		} else {
			addActionError("用户名已存在");
			return "add";
		}
	}	
	
	//更新页面
	@Action("traderUpdatePage")
	public String updatePage(){
		trader = traderService.getTrader(trader.getId());
		return "update";
	}
	
	//更新
	@Action("traderUpdate")
	public String update(){
		traderService.update(trader);
		return list();
	}
	
	//删除
	@Action("traderDelete")
	public String delete(){
		traderService.delete(trader);
		return list();
	}

	//禁言
	@Action("traderForbid")
	public String forbid(){
		traderService.forbid(trader.getId());
		return list();
	}
	
	//解禁
	@Action("traderReforbid")
	public String reforbid(){
		traderService.reforbid(trader.getId());
		return list();
	}
	
	//审核
	@Action("traderCheck")
	public String check(){
		traderService.check(trader.getId());
		return list();
	}
	
	
	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	public List<Trader> getTraders() {
		return traders;
	}

	public void setTraders(List<Trader> traders) {
		this.traders = traders;
	}


}
