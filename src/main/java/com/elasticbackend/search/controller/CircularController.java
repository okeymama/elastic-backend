package com.elasticbackend.search.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elasticbackend.search.dto.CircularDto;
import com.elasticbackend.search.service.CircularService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/App")
public class CircularController {
		
	@Autowired
	private CircularService circularService;
	
	@GetMapping("/Circulars-Paged")
	 public @ResponseBody ResponseEntity<Iterable<CircularDto>> getAllCircular() throws Exception{
		Iterable<CircularDto> circularDtos = circularService.getAllCircular();
		 return ResponseEntity.ok(circularDtos);
	 }
	
	@GetMapping("/Circular/{clientName}/{key:.+}")
	public @ResponseBody ResponseEntity<List<CircularDto>> getCircularMatch(@PathVariable("clientName") String clientName, @PathVariable("key") String key ){
		List<CircularDto> circularDtos = circularService.getCircularMatch(clientName, key);
		 return ResponseEntity.ok(circularDtos);
	}
	
	@PostMapping("/Circular")
	public @ResponseBody String saveCircular(@RequestBody CircularDto circularDto){
		if(null != circularDto && null == circularDto.getId()) { 
			Long id = new Date().getTime();
			circularDto.setId(id);	
		}
		circularDto.setCircularDetail(circularDto.getCircularDetail() + " >> " +circularDto.getDate());
		circularService.saveCircular(circularDto);
		return "Successfully Saved";
	}
	
	@GetMapping("/Duplicate-Circular/{clientName}/{circularNumber}")
	public Boolean checkDuplicateCircularNumber(@PathVariable("clientName")String clientName,@PathVariable("circularNumber")String circularNumber) {
		return circularService.checkDuplicateCircularNumber(clientName, circularNumber);
	}
	
	@GetMapping("/Circulars")
	public List<CircularDto> findAllCircular(){
		return circularService.findAllCircular();
	}
	
	@GetMapping("/Circulars/Count")
	public Long findAllCircularCount() {
		return circularService.findAllCircularCount();
	}
	
	@GetMapping("/Circulars/{createdBy}/Count")
	public Long findCircularCountByUser(@PathVariable("createdBy")String createdBy) {
		return circularService.findCircularCountByUser(createdBy);
	}
	
	@PostMapping("/Circulars-Match")
	public @ResponseBody ResponseEntity<List<CircularDto>> getCircularMatch(@RequestBody CircularDto circularDto){
		List<CircularDto> circularDtos = circularService.getCircularMatch(circularDto.getClientNumber(),circularDto.getCircularNumber(), circularDto.getCircularDetail(),circularDto.getDepartmant(),circularDto.getFileName());
		 return ResponseEntity.ok(circularDtos);
	}
	
}
