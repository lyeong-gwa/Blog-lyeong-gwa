package com.cogemutil.dto;

public class ImageUploadTry {
	String id_date;
	String id_hour;
	int cnt;
	public ImageUploadTry() {}
	public ImageUploadTry(String id_date, String id_hour, int cnt) {
		super();
		this.id_date = id_date;
		this.id_hour = id_hour;
		this.cnt = cnt;
	}
	public String getId_date() {
		return id_date;
	}
	public void setId_date(String id_date) {
		this.id_date = id_date;
	}
	public String getId_hour() {
		return id_hour;
	}
	public void setId_hour(String id_hour) {
		this.id_hour = id_hour;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
