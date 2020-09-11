package com.elasticbackend.search.dto;

import java.io.Serializable;

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

}
