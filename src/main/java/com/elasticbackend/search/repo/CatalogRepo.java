package com.elasticbackend.search.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.CatalogDto;



@Repository
public interface CatalogRepo extends ElasticsearchRepository<CatalogDto, String> {

}
