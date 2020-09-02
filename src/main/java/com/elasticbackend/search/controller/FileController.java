package com.elasticbackend.search.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elasticbackend.search.service.FileService;

@RestController
@RequestMapping(path = "/File")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/upload")
	public void uploadFile(@RequestHeader("clientName") String clientName,@RequestParam("file") MultipartFile file) throws IOException {
		fileService.uploadFile(clientName,file);
	}

	@GetMapping(path = { "/{clientName}/{fileName:.+}" })
	public void getFile(@PathVariable("clientName") String clientName, @PathVariable("fileName") String fileName,HttpServletResponse response) throws IOException {
		String fullFileName = fileName;
		byte[] file = fileService.getFile(clientName, fullFileName);
		ServletOutputStream outStream = response.getOutputStream();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+fullFileName);
		outStream.write(file);
		outStream.flush();
	}


}
