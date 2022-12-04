package com.cogemutil.comtroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Coregemstone;
import com.cogemutil.dto.ResultGetCoreList;
import com.cogemutil.service.CogemService;

@CrossOrigin("*")
@RestController
@RequestMapping("/coregem")
public class Cogemcontroller {

	@Autowired
	CogemService service_cogem;

	@PostMapping(value = "/get-core-list", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> getCoreList(HttpSession session, @RequestParam("job") String job,@RequestParam("job_kr") String job_kr,
			@RequestParam("upfile") MultipartFile[] files) {
		try {
			System.out.println("이미지처리 요청");
			ResultGetCoreList result_get_core_list = service_cogem.getCogemList(files, session.getId(), job);
			System.out.println("끝");
			result_get_core_list.setJob(job);
			result_get_core_list.setJob_kr(job_kr);
			return new ResponseEntity<Object>(result_get_core_list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping(value="/skill/{job}/{skill_name}")
	public ResponseEntity<?> showImage(@PathVariable("job") String job ,@PathVariable("skill_name") String skill_name) {
		try {
			//System.out.println(job+","+skill_name);
			Resource resource = service_cogem.getResourceImage(job,skill_name);
			
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", "image/png");
			
			return new ResponseEntity<Object>(resource, header,HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
