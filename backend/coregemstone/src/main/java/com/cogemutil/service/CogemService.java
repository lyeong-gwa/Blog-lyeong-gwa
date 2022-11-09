package com.cogemutil.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Coregemstone;
import com.cogemutil.dto.ResultGetCoreList;

public interface CogemService {
	public ResultGetCoreList getCogemList(MultipartFile[] files, String session_id,String job) throws IOException;
}
