package com.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.NoteDao;
import com.entity.Note;

@Service	// 注解为spring管理bean
@Transactional	//配置此方法加入事务管理
public class NoteService {

	@Resource
	private NoteDao noteDao;


	//获取所有
	public List<Note> getNoteList(int i, int size) {
		return noteDao.getNoteList(i, size);
	}

	//根据id获取
	public Note getNote(int id) {
		return noteDao.getNote(id);
	}

	//更新
	public boolean update(Note note) {
		note.setCreatetime(new Date());
		return noteDao.update(note);
	}

	//删除
	public boolean delete(Note note) {
		return noteDao.delete(note);
	}

	//添加
	public boolean add(Note note) {
		note.setCreatetime(new Date());
		return noteDao.save(note);
	}

	// 我的游记
	public List<Note> getNoteSelf(int userid, int i, int size) {
		return noteDao.getNoteList(userid, i, size);
	}

	//获取首页需要数据
	public List<Note> getIndexList(int count) {
		return noteDao.getIndexList(count);
	}

	// 获取记录总数
	public int getTotal() {
		return noteDao.getTotal();
	}
	
	// 获取记录总数
	public int getTotalSelf(int userid) {
		return noteDao.getTotalSelf(userid);
	}
}
