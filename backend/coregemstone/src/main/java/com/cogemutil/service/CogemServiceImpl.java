package com.cogemutil.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.CoreCalcTry;
import com.cogemutil.dto.Cogemstone;
import com.cogemutil.dto.ResultGetCoreList;
import com.cogemutil.mapper.CogemMapper;
import com.cogemutil.util.CogemUtil;

@Service
public class CogemServiceImpl implements CogemService {

	@Autowired
	CogemUtil cogemutil;

	@Autowired
	CogemMapper cogemmapper;

	@Override //핵심 이미지 처리 및 필요데이터 전송
	public ResultGetCoreList getCogemList(MultipartFile[] files, String job, String job_kr) throws IOException {
		ResultGetCoreList result_get_core_list = new ResultGetCoreList();
		cogemutil.getCogemList(files, job, result_get_core_list);// 이미지에 라벨링, 코어리스트에 img할당된 상태
		cogemutil.setCogemListLabeling(job, result_get_core_list); // 라벨링 리스트화 된 것을 보고 분석
		cogemutil.makeSkillMixImage(job, result_get_core_list); // 서버가 가지고 있지 않는 코어이미지라면 저장
		cogemutil.setJobSkillName(job, result_get_core_list); // 스킬리스트
		result_get_core_list.setJob(job); // 영어 라벨링
		result_get_core_list.setJob_kr(job_kr); // 한국어 추가
		
		cogemmapper.countImageUploadTry(); // 접근 카운트;
		return result_get_core_list;
	}

	@Override //필요한 이미지 전달
	public Resource getResourceImage(String job, String skill_name) {
		return cogemutil.getResourceImage(job, skill_name);
	}

	@Override //단순 데이터 수집 : 어떤 직업, 코어 개수, 선택한 스킬
	public int coreCalcTry(String job, int core_num, String select_skill, int combi_list_len) {
		return cogemmapper.countCoreCalcTry(job, core_num, select_skill, combi_list_len);
	}

}
