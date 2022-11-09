package com.cogemutil.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Coregemstone;
import com.cogemutil.dto.ResultGetCoreList;
import com.cogemutil.util.CogemUtil;

@Service
public class CogemServiceImpl implements CogemService{
	
	@Autowired
	CogemUtil cogemutil;
	
	@Override
	public ResultGetCoreList getCogemList(MultipartFile[] files,String session_id,String job) throws IOException {
		ResultGetCoreList result_get_core_list = new ResultGetCoreList();
		cogemutil.getCogemList(files,job,result_get_core_list);//이미지에 라벨링, 코어리스트에 img할당된 상태
		cogemutil.setCogemListLabeling(job,result_get_core_list);
		
		return result_get_core_list;
	}

}
