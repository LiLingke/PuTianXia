package com.putianxia.bin;

import java.util.List;

public class XiangQing {
	String imageurl="";
	String name="";
	String leftdecp="",rightdecp="";
	String weizhi="";
	List<String> listbiaoqian;
	
	
	
	public XiangQing(String imageurl, String name, String leftdecp,
			String rightdecp, String weizhi, List<String> listbiaoqian) {
		super();
		this.imageurl = imageurl;
		this.name = name;
		this.leftdecp = leftdecp;
		this.rightdecp = rightdecp;
		this.weizhi = weizhi;
		this.listbiaoqian = listbiaoqian;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLeftdecp() {
		return leftdecp;
	}
	public void setLeftdecp(String leftdecp) {
		this.leftdecp = leftdecp;
	}
	public String getRightdecp() {
		return rightdecp;
	}
	public void setRightdecp(String rightdecp) {
		this.rightdecp = rightdecp;
	}
	public String getWeizhi() {
		return weizhi;
	}
	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}
	public List<String> getListbiaoqian() {
		return listbiaoqian;
	}
	public void setListbiaoqian(List<String> listbiaoqian) {
		this.listbiaoqian = listbiaoqian;
	}
	
}
