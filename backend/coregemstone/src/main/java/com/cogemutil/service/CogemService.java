package com.cogemutil.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Cogemstone;
import com.cogemutil.dto.ResultGetCoreList;

public interface CogemService {
	public ResultGetCoreList getCogemList(MultipartFile[] files, String job, String job_kr) throws IOException;

	public Resource getResourceImage(String job, String skill_name);
	
	public int coreCalcTry(String job, int core_num, String select_skill, int combi_list_len);
}
