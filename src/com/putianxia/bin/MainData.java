package com.putianxia.bin;

import java.util.List;

public class MainData {
	private String title;
	List<XiangQing> list;	
	public MainData(String title, List<XiangQing> list) {
		super();
		this.title = title;
		this.list = list;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<XiangQing> getList() {
		return list;
	}
	public void setList(List<XiangQing> list) {
		this.list = list;
	}
	
}
