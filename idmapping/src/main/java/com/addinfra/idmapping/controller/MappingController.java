package com.addinfra.idmapping.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.addinfra.idmapping.service.HdfsPutService;
import com.addinfra.idmapping.service.SparkService;

@Controller
public class MappingController {
	@Autowired
	private SparkService sparkService;
	
	@Value("${file.location}")
	private String fileLocation;
		
	@RequestMapping("/uid_mapping")
	public String fileupload(Model model, @RequestParam("filename") MultipartFile[] files) {
		// 로컬 파일로 저장
		try {
			for (MultipartFile file : files) {
				file.transferTo(new File(fileLocation + "/" + file.getOriginalFilename()));	
			}
		
		} catch(IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		// 로컬 파일 하둡에 쓰기
		//HdfsPutService.executeScript();
		
		// spark 돌리기
		sparkService.runSpark();
	
		// spark 결과 로컬 파일로 쓰기
		sparkService.copyToLocal();
		
		// 필요 없는 파일 정리
		
		// 결과 파일을 전달.
		List<String> list = new ArrayList<>();
		list.add("aaaaa");
		list.add("bbbbb");
		model.addAttribute("files", list);
		
		return "result.html";
	}

}