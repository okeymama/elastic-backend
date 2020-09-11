package com.elasticbackend.search.service;

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
}