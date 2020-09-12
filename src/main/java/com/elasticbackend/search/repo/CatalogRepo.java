package com.elasticbackend.search.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.CatalogDto;



@Repository
public interface CatalogRepo extends ElasticsearchRepository<CatalogDto, String> {


	Optional<List<CatalogDto>> findByModelNo(String modelNo);
	
	
	List<CatalogDto> findByProductNameOrModelNoOrOldModelNoOrVoltageOrRangeOrColourOrFileName
	(String productName,String modelNo,String oldModelNo,String voltage,String range,String colour,String fileName);

}
