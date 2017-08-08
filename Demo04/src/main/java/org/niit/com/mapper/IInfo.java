package org.niit.com.mapper;

import java.util.List;

import org.niit.com.bean.Info;

public interface IInfo {
	
	public List<Info> queryInfos();
	
	public Info queryInfo(int id);
	
	public int insertInfo(Info info);
	
	public int deleteInfo(int id);
	
	public int updateInfo(Info info);
	
}
