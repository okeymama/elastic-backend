package com.elasticbackend.search.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName="enquiry",type="enquiryDto",shards=2)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryDto {

	private String companyName;

	private String personName;

	private String mobile;

	private String place;

	@Id
	private String enquiryNumber;

	private String date;

	private String itemDescription;

	private String make;

	private String status;

	private String remark;
	
	private String fileName;

}
