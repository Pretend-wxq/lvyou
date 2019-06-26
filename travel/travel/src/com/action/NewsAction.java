package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.News;
import com.service.NewsService;

@Namespace("/admin")
@Results({
	@Result(name="list",location="/admin/pages/news-list.jsp"),
	@Result(name="update",location="/admin/pages/news-update.jsp"),
})
@SuppressWarnings("serial")
public class NewsAction extends BaseAction{
	
	private News news;
	private List<News> newses;
	@Resource
	private NewsService newsService;
	
	
	//获取列表
	@Action("newsList")
	public String list(){
		newses = newsService.getNewsList((page-1)*size, size);
		pages = getPages(newsService.getTotal(), size);
		return "list";
	}
	
	//添加
	@Action("newsAdd")
	public String add(){
		newsService.add(news);
		return list();
	}	
	
	//更新页面
	@Action("newsUpdatePage")
	public String updatePage(){
		news = newsService.getNews(news.getId());
		return "update";
	}
	
	//更新
	@Action("newsUpdate")
	public String update(){
		newsService.update(news);
		return list();
	}
	
	//删除
	@Action("newsDelete")
	public String delete(){
		newsService.delete(news);
		return list();
	}

	
	
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<News> getNewses() {
		return newses;
	}

	public void setNewss(List<News> newses) {
		this.newses = newses;
	}


}
