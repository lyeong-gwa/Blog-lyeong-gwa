package com.cogemutil.dto;

public class CoreCalcTry {
	int id;
	String job;
	int core_num;
	String select_skill;
	int combi_list_len;
	public CoreCalcTry() {
	}
	public CoreCalcTry(int id, String job, int core_num, String select_skill, int combi_list_len) {
		super();
		this.id = id;
		this.job = job;
		this.core_num = core_num;
		this.select_skill = select_skill;
		this.combi_list_len = combi_list_len;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getCore_num() {
		return core_num;
	}
	public void setCore_num(int core_num) {
		this.core_num = core_num;
	}
	public String getSelect_skill() {
		return select_skill;
	}
	public void setSelect_skill(String select_skill) {
		this.select_skill = select_skill;
	}
	public int getCombi_list_len() {
		return combi_list_len;
	}
	public void setCombi_list_len(int combi_list_len) {
		this.combi_list_len = combi_list_len;
	}


}
