package com.cogemutil.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.cogemutil.dto.Cogemstone;
import com.cogemutil.dto.ResultGetCoreList;
import com.cogemutil.service.CogemService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/coregem")
public class CogemController {

	@Autowired
	CogemService service_cogem;
	
	@GetMapping("/")
	public ResponseEntity<?> getHeartBeat(){
		return new ResponseEntity<String>("Alive", HttpStatus.OK);
	}
	
	@PostMapping(value = "/get-core-list", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> getCoreList(@RequestParam("job") String job, @RequestParam("job_kr") String job_kr,
			@RequestParam("upfile") MultipartFile[] files) {
		try {
			System.out.println("이미지처리 요청");
			ResultGetCoreList result_get_core_list = service_cogem.getCogemList(files, job, job_kr);
			System.out.println("끝");
			return new ResponseEntity<Object>(result_get_core_list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping(value = "/skill/{job}/{skill_name}") // 특정 직업에 대한 스킬 이미지 요청
	public ResponseEntity<?> showImage(@PathVariable("job") String job, @PathVariable("skill_name") String skill_name) {
		try {
			Resource resource = service_cogem.getResourceImage(job, skill_name);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", "image/png");

			return new ResponseEntity<Object>(resource, header, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	
	@PostMapping(value = "/data_get")
	public ResponseEntity<?> coreCalcTry(@RequestParam("job") String job, @RequestParam("core_num") int core_num,
			@RequestParam("select_skill") String select_skill,@RequestParam("combi_list_len") int combi_list_len) {
		try {
			int result = service_cogem.coreCalcTry(job, core_num, select_skill, combi_list_len);
			if (result == 0) {
				System.out.println("적용x");
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
