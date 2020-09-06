package com.elasticbackend.search.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName="milkyway",type="circular",shards=3)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CircularDto implements Serializable{

	private static final long serialVersionUID = 8238635599710674545L;
	
	@Id
	private Long  id;
	
	private String clientNumber;

	private String circularNumber;

	private String date;

	private String circularDetail;

	private String departmant;
	
	private String fileName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getCircularNumber() {
		return circularNumber;
	}

	public void setCircularNumber(String circularNumber) {
		this.circularNumber = circularNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCircularDetail() {
		return circularDetail;
	}

	public void setCircularDetail(String circularDetail) {
		this.circularDetail = circularDetail;
	}

	public String getDepartmant() {
		return departmant;
	}

	public void setDepartmant(String departmant) {
		this.departmant = departmant;
	}


}

