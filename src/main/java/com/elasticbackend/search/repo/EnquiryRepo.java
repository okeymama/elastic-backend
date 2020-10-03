package com.elasticbackend.search.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.EnquiryDto;

@Repository
public interface EnquiryRepo extends ElasticsearchRepository<EnquiryDto, String> {

	Optional<List<EnquiryDto>> findByEnquiryNumber(String enquiryNumber);
	
	List<EnquiryDto> findByCompanyNameOrPersonNameOrMobileOrPlaceOrEnquiryNumberOrItemDescriptionOrMakeOrStatusOrRemarkOrFileNameOrCreatedBy
	(String companyName,String personName,String mobile,String place,String enquiryNumber,String itemDescription,String make,String status,String remark,String fileName, String createdBy,PageRequest pageRequest);

	List<EnquiryDto> findByCompanyNameAndPersonNameAndMobileAndPlaceAndEnquiryNumberAndItemDescriptionAndMakeAndStatusAndRemarkAndFileNameAndCreatedBy
	(String companyName,String personName,String mobile,String place,String enquiryNumber,String itemDescription,String make,String status,String remark,String fileName, String createdBy,PageRequest pageRequest);

}

