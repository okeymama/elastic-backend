package com.elasticbackend.search.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elasticbackend.search.repo.FileRepo;

@Service
public class FileService {

	@Autowired
	FileRepo fileRepo;
	
	final String appName = "fileStore";
	final String directory = "C:\\Users\\cchaubey\\Documents\\app_service";

	public String uploadFile(String clientName,MultipartFile file) throws IOException {
		Long id = new Date().getTime();
		System.out.println("Original File Byte Size - " + file.getBytes().length);
		String fileName = createFileName(id, file.getOriginalFilename());
		String path = directory + File.separator + appName + File.separator + clientName;
		String absolutePath = path + File.separator +  fileName;
		File fileMk = new File(path);
		System.out.println(fileMk.mkdirs());
		try(FileOutputStream fileOutputStream = new FileOutputStream(absolutePath)) {
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bufferedOutputStream.write(file.getBytes());
			System.out.println(absolutePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
		return fileName;
	}


	private String createFileName(Long id, String fileName) {
		String[] splitFileName = fileName.split("\\.");
		fileName = splitFileName[0]+"-"+id+"."+splitFileName[1];
		return fileName;
	}


	public byte[] getFile(String clientName,String fileName) throws IOException {
		String dirPath = directory + File.separator + appName + File.separator + clientName;
		Path path = Paths.get(dirPath,fileName);
		return Files.readAllBytes(path);
	}






}
