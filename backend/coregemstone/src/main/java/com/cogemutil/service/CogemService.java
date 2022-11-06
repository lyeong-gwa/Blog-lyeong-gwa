package com.cogemutil.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Coregemstone;

public interface CogemService {
	public List<Coregemstone> getCogemList(MultipartFile[] files) throws IOException;
}
