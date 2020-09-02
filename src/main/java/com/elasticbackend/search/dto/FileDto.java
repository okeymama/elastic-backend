package com.elasticbackend.search.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName="universe",type="fileDto",shards=2)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

	public FileDto(String originalFilename, String contentType, String compressBytes) {
		this.name = originalFilename;
		this.type = contentType;
		this.picByte = compressBytes;
	}
	
	@Id
	private Long id;
	private String name;
	private String type;
	private String picByte;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPicByte() {
		return picByte;
	}
	public void setPicByte(String picByte) {
		this.picByte = picByte;
	}
	
	
	
}
