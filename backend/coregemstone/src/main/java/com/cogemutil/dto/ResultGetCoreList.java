package com.cogemutil.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultGetCoreList {
	int[] skill_level;
	List<Coregemstone> core_list;
	List<String> request_image_list;
	
	
	public ResultGetCoreList() {
		this.core_list = new ArrayList<>();
		this.request_image_list = new ArrayList<>();
	}
	public ResultGetCoreList(int[] skill_level, List<Coregemstone> core_list, List<String> request_image_list) {
		super();
		this.skill_level = skill_level;
		this.core_list = core_list;
		this.request_image_list = request_image_list;
	}
	public int[] getSkill_level() {
		return skill_level;
	}
	public void setSkill_level(int[] skill_level) {
		this.skill_level = skill_level;
	}
	public List<Coregemstone> getCore_list() {
		return core_list;
	}
	public void setCore_list(List<Coregemstone> core_list) {
		this.core_list = core_list;
	}
	public List<String> getRequest_image_list() {
		return request_image_list;
	}
	public void setRequest_image_list(List<String> request_image_list) {
		this.request_image_list = request_image_list;
	}
	public void addRequest_image_list(String base64_img) {
		this.request_image_list.add(base64_img);
	}
	
	
}
