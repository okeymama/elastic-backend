package com.elasticbackend.search.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName="purchaseorder",type="purchaseOrderDto",shards=2)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDto {

	@Id
	private String orderNo;

	private String item;

	private String make;

	private String modelNo;	

	private String quantity;

	private String rate;

	private String remark;

	private String date;

	private String itemCode;

	private String customer;

	private String fileName;
	
}
