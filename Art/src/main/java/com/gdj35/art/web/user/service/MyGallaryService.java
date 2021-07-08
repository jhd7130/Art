package com.gdj35.art.web.user.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdj35.art.web.user.dao.IMyGallaryDao;

@Service
public class MyGallaryService implements IMyGallaryService{
	@Autowired
	public IMyGallaryDao iMyGallaryDao;

	@Override
	public int addPost(HashMap<String, String> params) throws Throwable {
		
		return iMyGallaryDao.addPost(params);
	}

	@Override
	public int getNum() throws Throwable {
		return iMyGallaryDao.getNum();
	}

	@Override
	public List<HashMap<String, String>> picList(HashMap<String, String> params) throws Throwable {
		return iMyGallaryDao.picList(params);
	}

	@Override
	public int getPicCnt(HashMap<String, String> params) throws Throwable {
		return iMyGallaryDao.getPicCnt(params);
	}

	@Override
	public List<HashMap<String, String>> drawList(HashMap<String, String> params) throws Throwable {
		return iMyGallaryDao.drawList(params);
	}

	@Override
	public int getDrawCnt(HashMap<String, String> params) throws Throwable {
		return iMyGallaryDao.getDrawCnt(params);
	}
	


	
}
