package com.cogemutil.comtroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Coregemstone;
import com.cogemutil.dto.ResultGetCoreList;
import com.cogemutil.service.CogemService;

@RestController
@RequestMapping("/coregem")
public class Cogemcontroller {

	@Autowired
	CogemService service_cogem;

	@PostMapping(value = "/get-core-list", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> getCoreList(HttpSession session, @RequestParam("job") String job,
			@RequestParam("upfile") MultipartFile[] files) {
		try {
			ResultGetCoreList result_get_core_list = service_cogem.getCogemList(files, session.getId(), job);
			return new ResponseEntity<Object>(result_get_core_list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
