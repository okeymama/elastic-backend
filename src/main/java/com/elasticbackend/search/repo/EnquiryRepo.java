package com.elasticbackend.search.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.EnquiryDto;

@Repository
public interface EnquiryRepo extends ElasticsearchRepository<EnquiryDto, String> {

}
