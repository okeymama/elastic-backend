package com.elasticbackend.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elasticbackend.search.dto.CircularDto;
import com.elasticbackend.search.service.CircularService;

@RestController
@RequestMapping("/App")
public class CircularController {
		
	@Autowired
	private CircularService circularService;
	
	@GetMapping("/Circular")
	 public @ResponseBody ResponseEntity<List<CircularDto>> getAllCircular() throws Exception{
		List<CircularDto> circularDtos = circularService.getAllCircular();
		 return ResponseEntity.ok(circularDtos);
	 }
	
	@GetMapping("/Circular/{key}")
	public @ResponseBody ResponseEntity<List<CircularDto>> getCircularMatch(@PathVariable("key") String key){
		List<CircularDto> circularDtos = circularService.getCircularMatch(key);
		 return ResponseEntity.ok(circularDtos);
	}
	
	@PostMapping("/Circular")
	 public @ResponseBody String saveCircular(@RequestBody CircularDto circularDto){
		 circularService.saveCircular(circularDto);
		 return "Successfully Saved";
	 }
	
}
