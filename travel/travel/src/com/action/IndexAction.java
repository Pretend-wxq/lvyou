package com.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Gbook;
import com.entity.News;
import com.entity.Note;
import com.entity.Place;
import com.service.GbookService;
import com.service.NewsService;
import com.service.NoteService;
import com.service.PlaceService;
import com.util.UploadUtil;

@Namespace("/index")
@Results({
	@Result(name="index",location="/index/index.jsp"),
	@Result(name="placeList",location="/index/place.jsp"),
	@Result(name="placeSelf",location="/index/pages/place-self.jsp"),
	@Result(name="placeIntro",location="/index/pages/place-intro.jsp"),
	@Result(name="placeEdit",location="/index/pages/place-edit.jsp"),
	@Result(name="newsList",location="/index/news.jsp"),
	@Result(name="newsAdd",location="/index/pages/news-add.jsp"),
	@Result(name="newsIntro",location="/index/pages/news-intro.jsp"),
	@Result(name="newsSelf",location="/index/pages/news-self.jsp"),
	@Result(name="newsEdit",location="/index/pages/news-edit.jsp"),
	@Result(name="noteList",location="/index/note.jsp"),
	@Result(name="noteAdd",location="/index/pages/note-add.jsp"),
	@Result(name="noteIntro",location="/index/pages/note-intro.jsp"),
	@Result(name="noteSelf",location="/index/pages/note-self.jsp"),
	@Result(name="noteEdit",location="/index/pages/note-edit.jsp"),
	@Result(name="gbookList",location="/index/gbook.jsp"),
	@Result(name="leaveSelf",location="/index/pages/leave-self.jsp"),
})
@SuppressWarnings("serial")
public class IndexAction extends BaseAction{
	
	private int userid;
	private Place place;
	private News news;
	private Note note;
	private Gbook gbook;
	private List<Place> places;
	private List<News> newses;
	private List<Note> notes;
	private List<Gbook> gbooks;
	@Resource
	private PlaceService placeService;
	@Resource
	private NewsService newsService;
	@Resource
	private NoteService noteService;
	@Resource
	private GbookService gbookService;
	
	private File photo;		//获取上传文件
    private String photoFileName;	//获取上传文件名称

    
    // 首页
    @Action("index")
    public String execute(){
    	places = placeService.getIndexList(4);
    	newses = newsService.getIndexList(4);
    	notes = noteService.getIndexList(4);
    	return "index";
    }
    
	// 景点列表
    @Action("placeList")
	public String placeList(){
		places = placeService.getPlaceList((page-1)*size, size);
		pages = getPages(placeService.getTotal(), size);
		return "placeList";
	}
	
	// 优惠列表
    @Action("newsList")
	public String newsList(){
		newses = newsService.getNewsList((page-1)*size, size);
		pages = getPages(newsService.getTotal(), size);
		return "newsList";
	}
	
	// 游记列表
    @Action("noteList")
	public String noteList(){
		notes = noteService.getNoteList((page-1)*size, size);
		pages = getPages(noteService.getTotal(), size);
		return "noteList";
	}
	
	// 留言列表
    @Action("gbookList")
	public String gbookList(){
		gbooks = gbookService.getList((page-1)*size, size);
		pages = getPages(gbookService.getTotal(), size);
		return "gbookList";
	}
	
	// 景点介绍
    @Action("placeIntro")
	public String placeIntro(){
		place = placeService.getPlace(place.getId());
		return "placeIntro";
	}
	
	// 优惠介绍
    @Action("newsIntro")
	public String newsIntro(){
		news = newsService.getNews(news.getId());
		return "newsIntro";
	}
	
	// 游记介绍
    @Action("noteIntro")
	public String noteIntro(){
		note = noteService.getNote(note.getId());
		return "noteIntro";
	}
	
	// 我的优惠
    @Action("newsSelf")
	public String newsSelf(){
		newses = newsService.getNewsSelf(userid,(page-1)*size, size);
		pages = getPages(newsService.getTotalSelf(userid), size);
		return "newsSelf";
	}
	
	// 我的游记
    @Action("noteSelf")
	public String noteSelf(){
		notes = noteService.getNoteSelf(userid,(page-1)*size, size);
		pages = getPages(noteService.getTotalSelf(userid), size);
		return "noteSelf";
	}
	
	// 添加游记
    @Action("noteAddpage")
	public String noteAddpage(){
		return "noteAdd";
	}
	
	// 添加游记
    @Action("noteAdd")
	public String noteAdd(){
		if (photo != null) {
			note.setPhoto(UploadUtil.fileUpload(photo, photoFileName, "photo/note"));
		}
		noteService.add(note);
		return noteSelf();
	}

	// 添加留言
    @Action("gbookAdd")
	public String gbookAdd(){
		gbookService.add(gbook);
		return gbookList();
	}

	
	// 添加优惠
    @Action("newsAddpage")
	public String newsAddpage(){
		return "newsAdd";
	}
	
	// 添加优惠
    @Action("newsAdd")
	public String newsAdd(){
		if (photo != null) {
			news.setPhoto(UploadUtil.fileUpload(photo, photoFileName, "photo/news"));
		}
		newsService.add(news);
		return newsSelf();
	}
	
	// 删除优惠
    @Action("newsDrop")
	public String newsDrop(){
		newsService.delete(news);
		return newsSelf();
	}
	
	// 删除游记
    @Action("noteDrop")
	public String noteDrop(){
		noteService.delete(note);
		return noteSelf();
	}
	
	// 编辑优惠
    @Action("newsEdit")
	public String newsEdit(){
		news = newsService.getNews(news.getId());
		return "newsEdit";
	}
	
	// 编辑游记
    @Action("noteEdit")
	public String noteEdit(){
		note = noteService.getNote(note.getId());
		return "noteEdit";
	}
	
	// 保存优惠
    @Action("newsSave")
	public String newsSave(){
		if (photo != null) {
			news.setPhoto(UploadUtil.fileUpload(photo, photoFileName, "photo/news"));
		}
		newsService.update(news);
		return newsSelf();
	}
	
	// 保存游记
    @Action("noteSave")
	public String noteSave(){
		if (photo != null) {
			note.setPhoto(UploadUtil.fileUpload(photo, photoFileName, "photo/note"));
		}
		noteService.update(note);
		return noteSelf();
	}
	
	
	public List<News> getNewses() {
		return newses;
	}
	public void setNewses(List<News> newses) {
		this.newses = newses;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	public Gbook getGbook() {
		return gbook;
	}
	public void setGbook(Gbook gbook) {
		this.gbook = gbook;
	}
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public List<Gbook> getGbooks() {
		return gbooks;
	}
	public void setGbooks(List<Gbook> gbooks) {
		this.gbooks = gbooks;
	}

}
