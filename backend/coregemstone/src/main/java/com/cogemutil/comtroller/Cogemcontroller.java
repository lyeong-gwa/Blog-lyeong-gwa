package com.cogemutil.comtroller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Coregemstone;
import com.cogemutil.service.CogemService;


@RestController
@RequestMapping("/coregem")
public class Cogemcontroller {

	@Autowired
	CogemService service_cogem;
	
	@PostMapping("/get-core-list")
	public ResponseEntity<?> getCoreList(@RequestParam Map<String, String> map, @RequestParam("upfile") MultipartFile[] files){
		try {
			
			service_cogem.getCogemList(files);
			
			return new ResponseEntity<Object>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	} 
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
