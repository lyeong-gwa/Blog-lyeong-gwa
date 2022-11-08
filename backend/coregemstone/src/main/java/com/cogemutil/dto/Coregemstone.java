package com.cogemutil.dto;

public class Coregemstone {
	private int core_id;
	private int level;
	private int[] skill_data;// [왼쪽(메인),중간,오른쪽]
	public Coregemstone() {}
	public Coregemstone(int core_id, int level, int[] skill_data) {
		super();
		this.core_id = core_id;
		this.level = level;
		this.skill_data = skill_data;
	}
	public int getCore_id() {
		return core_id;
	}
	public void setCore_id(int core_id) {
		this.core_id = core_id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int[] getSkill_data() {
		return skill_data;
	}
	public void setSkill_data(int[] skill_data) {
		this.skill_data = skill_data;
	}

}
