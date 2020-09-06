package com.elasticbackend.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.elasticbackend.search.dto.CircularDto;
import com.elasticbackend.search.repo.CircularRepo;

@Service
public class CircularService {
	
	@Autowired
	private CircularRepo circularRepo; 

	public Boolean checkDuplicateCircularNumber(String clientName,String circularNumber) {
		Boolean flag = false;
		if(circularRepo.findByClientNumberAndCircularNumber(clientName,circularNumber).isPresent()) {
			List<CircularDto> circularDtos = circularRepo.findByClientNumberAndCircularNumber(clientName,circularNumber).get();
			Long count = circularDtos.stream().filter(cd->cd.getClientNumber().equals(clientName) 
						&& cd.getCircularNumber().equals(circularNumber)).count();
			if(count>0) {
				flag = true;
			}
		}
		return flag ;
	}
	
	
	public Page<CircularDto> getCircularMatch(String keyWord, int page, int limit) {
		Pageable pageable = PageRequest.of(page, limit);
		QueryBuilder queryBuilder =
		QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(keyWord).analyzeWildcard(true)
        .field("circularDetail"));
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		nativeSearchQueryBuilder.withQuery(queryBuilder).withPageable(pageable);
		NativeSearchQuery query = nativeSearchQueryBuilder.build();
		return circularRepo.search(query);
	}
	
	
	public List<CircularDto> getCircularMatch(String clientName, String keyWord){
		List<CircularDto> circularDtos = circularRepo.findByCircularNumberOrDateOrCircularDetailOrDepartmantOrFileName(keyWord, keyWord, keyWord, keyWord, keyWord);
		return circularDtos.stream().filter(x -> x.getClientNumber().equals(clientName)).collect(Collectors.toList());
	}
	
	public List<CircularDto> getAllCircular() {
		Iterable<CircularDto> circularDtoIter =  circularRepo.findAll();
		List<CircularDto> circularDtos = new ArrayList<>();
		circularDtoIter.iterator().forEachRemaining(circularDtos::add);
		return circularDtos ;
	}
	
	public void saveCircular(CircularDto circularDto) {
		 circularRepo.save(circularDto);
	}
	
	

}
