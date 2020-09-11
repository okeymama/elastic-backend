package com.elasticbackend.search.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elasticbackend.search.dto.EnquiryDto;
import com.elasticbackend.search.repo.EnquiryRepo;

@Service
public class EnquiryService {

	@Autowired 
	private EnquiryRepo enquiryRepo;

	public Iterable<EnquiryDto> getAllEnquiry(){
		Iterable<EnquiryDto> enquirys = enquiryRepo.findAll();
		return enquirys;
	}

	public void saveEnquirys(EnquiryDto enquiryDto) {
		enquiryRepo.save(enquiryDto);
	}

	public Boolean checkDuplicateEnquiry(String enquiryNumber) {
		Boolean flag = false;
		Optional<List<EnquiryDto>> enquirysOption = enquiryRepo.findByEnquiryNumber(enquiryNumber);
		if(enquirysOption.isPresent()) {
			List<EnquiryDto> enquiry = enquirysOption.get();
			Long count = enquiry.stream().filter(x->x.getEnquiryNumber().equalsIgnoreCase(enquiryNumber)).count();
			if(count>0)flag=true;
		}
		return flag;
	}
	
}