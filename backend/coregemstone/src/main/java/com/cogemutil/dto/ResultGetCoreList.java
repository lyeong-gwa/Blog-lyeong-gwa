package com.cogemutil.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultGetCoreList {
	private String job;
	private String job_kr;
	private List<Coregemstone> core_list;
	private List<String> request_image_list;
	private List<String> skill_name;
	
	public ResultGetCoreList() {
		this.job="";
		this.job="job_kr";
		this.core_list = new ArrayList<>();
		this.request_image_list = new ArrayList<>();
		this.skill_name = new ArrayList<>();
	}
	public ResultGetCoreList(String job,String job_kr,List<Coregemstone> core_list, List<String> request_image_list,List<String> skill_name) {
		super();
		this.job = job;
		this.job_kr = job_kr;
		this.core_list = core_list;
		this.request_image_list = request_image_list;
		this.skill_name = skill_name;
	}
	
	public List<String> getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(List<String> skill_name) {
		this.skill_name = skill_name;
	}
	public String getJob_kr() {
		return job_kr;
	}
	public void setJob_kr(String job_kr) {
		this.job_kr = job_kr;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
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
	
	public void addSkillName(String skill_name) {
		this.skill_name.add(skill_name);
	}
}
