package com.elasticbackend.search.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elasticbackend.search.dto.EnquiryDto;
import com.elasticbackend.search.repo.EnquiryRepo;
import com.elasticbackend.search.util.SearchUtil;

@Service
public class EnquiryService {

	@Autowired 
	private EnquiryRepo enquiryRepo;

	public Iterable<EnquiryDto> getAllEnquiry(){
		Iterable<EnquiryDto> enquirys = enquiryRepo.findAll(new PageRequest(0, 500));
		return enquirys;
	}

	public void saveEnquirys(EnquiryDto enquiryDto) {
		if(null != enquiryDto.getRemark()) {
			enquiryDto.setRemark(enquiryDto.getRemark() + " >> " + enquiryDto.getDate());
		}
		if(null == enquiryDto.getCreationDate()) {
			enquiryDto.setCreationDate(new Date());
		}
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
	
	public List<EnquiryDto> getMatchingEnquirys(String key){
		List<EnquiryDto> enquirys = enquiryRepo.findByCompanyNameOrPersonNameOrMobileOrPlaceOrEnquiryNumberOrItemDescriptionOrMakeOrStatusOrRemarkOrFileNameOrCreatedBy
				(key, key, key, key, key, key, key, key, key, key,key, new PageRequest(0, 500));
		return enquirys;
	}

	public List<EnquiryDto> getMatchingEnquirys(String companyName,String personName,String mobile,String place,String enquiryNumber,String itemDescription,String make,String status,String remark,String fileName, String createdBy){
		
		companyName = SearchUtil.setDefaultAsterisk(companyName);
		personName = SearchUtil.setDefaultAsterisk(personName);
		mobile = SearchUtil.setDefaultAsterisk(mobile);
		place = SearchUtil.setDefaultAsterisk(place);
		enquiryNumber = SearchUtil.setDefaultAsterisk(enquiryNumber);
		itemDescription = SearchUtil.setDefaultAsterisk(itemDescription);
		make = SearchUtil.setDefaultAsterisk(make);
		status = SearchUtil.setDefaultAsterisk(status);
		remark = SearchUtil.setDefaultAsterisk(remark);
		fileName = SearchUtil.setDefaultAsterisk(fileName);
		createdBy = SearchUtil.setDefaultAsterisk(createdBy);
		
		List<EnquiryDto> enquirys = enquiryRepo.findByCompanyNameAndPersonNameAndMobileAndPlaceAndEnquiryNumberAndItemDescriptionAndMakeAndStatusAndRemarkAndFileNameAndCreatedBy
				(companyName, personName, mobile,place ,enquiryNumber , itemDescription, make, status, remark, fileName, createdBy,new PageRequest(0, 500));
		return enquirys;
	}
	
}