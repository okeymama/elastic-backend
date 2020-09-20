package com.elasticbackend.search.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName="catalogdto",type="catalogDto",shards=3)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String productName;

	@Id
	private String modelNo;

	private String oldModelNo;

	private String voltage;

	private String range;	

	private String colour;

	private String fileName;
	
	private Date creationDate;
	
	private String createdBy;
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getOldModelNo() {
		return oldModelNo;
	}

	public void setOldModelNo(String oldModelNo) {
		this.oldModelNo = oldModelNo;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
