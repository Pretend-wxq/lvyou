package com.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Place;
import com.service.PlaceService;
import com.util.UploadUtil;

@Namespace("/admin")
@Results({
	@Result(name="list",location="/admin/pages/place-list.jsp"),
	@Result(name="update",location="/admin/pages/place-update.jsp"),
})
@SuppressWarnings("serial")
public class PlaceAction extends BaseAction{
	
	private Place place;
	private List<Place> places;
	@Resource
	private PlaceService placeService;
	
	private File photo;		//获取上传文件
    private String photoFileName;	//获取上传文件名称
	
	//获取列表
    @Action("placeList")
	public String list(){
		places = placeService.getPlaceList((page-1)*size, size);
		pages = getPages(placeService.getTotal(), size);
		return "list";
	}
	
	//添加
    @Action("placeAdd")
	public String add(){
		if (photo != null) {
			place.setPhoto(UploadUtil.fileUpload(photo, photoFileName, "photo/place"));
		}
		placeService.add(place);
		return list();
	}	
	
	//更新页面
    @Action("placeUpdatePage")
	public String updatePage(){
		place = placeService.getPlace(place.getId());
		return "update";
	}
	
	//更新
    @Action("placeUpdate")
	public String update(){
		if(photo != null){
			place.setPhoto(UploadUtil.fileUpload(photo, photoFileName, "photo/place"));
		}
		placeService.update(place);
		return list();
	}
	
	//删除
    @Action("placeDelete")
	public String delete(){
		placeService.delete(place);
		return list();
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


}
