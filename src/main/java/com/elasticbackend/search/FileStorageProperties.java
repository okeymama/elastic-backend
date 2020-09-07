package com.elasticbackend.search;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@Configurable
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    
	private String uploadFolder;

	public String getUploadFolder() {
		return uploadFolder;
	}

	public void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}
	
}
