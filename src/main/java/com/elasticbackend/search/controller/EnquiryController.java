package com.elasticbackend.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elasticbackend.search.dto.EnquiryDto;
import com.elasticbackend.search.service.EnquiryService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/App")
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;

	@GetMapping("/Enquiry")
	public Iterable<EnquiryDto>	getAllEnquiry(){
		return enquiryService.getAllEnquiry();
	}

	@PostMapping("/Enquiry")
	public void	saveEnquirys(@RequestBody EnquiryDto enquiryDto){
		enquiryService.saveEnquirys(enquiryDto);
	}

	@PostMapping("/Duplicate-Enquiry")
	public Boolean checkDuplicateEnquiry(@RequestBody String enquiryNumber){
		return enquiryService.checkDuplicateEnquiry(enquiryNumber);
	}
	
	@GetMapping("/Enquiry/{key}")
	public List<EnquiryDto>	getMatchingEnquirys(@PathVariable("key")String key){
		return enquiryService.getMatchingEnquirys(key);
	}

	
}




