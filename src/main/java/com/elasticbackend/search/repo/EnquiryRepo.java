package com.elasticbackend.search.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.EnquiryDto;

@Repository
public interface EnquiryRepo extends ElasticsearchRepository<EnquiryDto, String> {

	Optional<List<EnquiryDto>> findByEnquiryNumber(String enquiryNumber);

}
