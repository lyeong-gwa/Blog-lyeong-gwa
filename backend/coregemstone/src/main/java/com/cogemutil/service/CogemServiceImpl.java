package com.cogemutil.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Coregemstone;
import com.cogemutil.util.CogemUtil;

@Service
public class CogemServiceImpl implements CogemService{
	
	@Autowired
	CogemUtil cogemutil;
	
	@Override
	public List<Coregemstone> getCogemList(MultipartFile[] files) throws IOException {
		List<Coregemstone> cogem_list = cogemutil.getCogemList(files);
		return null;
	}

}
