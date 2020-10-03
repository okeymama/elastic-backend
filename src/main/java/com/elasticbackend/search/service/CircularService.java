package com.elasticbackend.search.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import com.elasticbackend.search.util.SearchUtil;

@Service
public class CircularService {
	
	@Autowired
	private CircularRepo circularRepo; 

	public Boolean checkDuplicateCircularNumber(String clientName,String circularNumber) {
		Boolean flag = false;
		Optional<List<CircularDto>> circular = circularRepo.findByClientNumberAndCircularNumber(clientName,circularNumber);
		if(circular.isPresent()) {
			List<CircularDto> circularDtos = circular.get();
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
		List<CircularDto> circularDtos = circularRepo.findByCircularNumberOrCircularDetailOrDepartmantOrFileNameOrCreatedBy(keyWord, keyWord, keyWord, keyWord,keyWord,new PageRequest(0, 500));
		return circularDtos.stream().filter(x -> x.getClientNumber().equals(clientName)).collect(Collectors.toList());
	}
	
	public Iterable<CircularDto> getAllCircular() {
		Iterable<CircularDto> circularDtoIter =  circularRepo.findAll(new PageRequest(0, 500));
		return circularDtoIter ;
	}
	
	public void saveCircular(CircularDto circularDto) {
		if(null == circularDto.getCreationDate()) {
			circularDto.setCreationDate(new Date());
		}
		 circularRepo.save(circularDto);
	}
	
	public List<CircularDto> findAllCircular(){
		return circularRepo.findAllCircular();
	}
	
	public Long findAllCircularCount() {
		return circularRepo.findAllCircularCount();
	}
	
	public Long findCircularCountByUser(String createdBy) {
		return circularRepo.countByCreatedBy(createdBy);
	}
	
	public List<CircularDto> getCircularMatch(String clientName, String circularNumber,String circularDetail,String departmant,String fileName, String createdBy){
		circularNumber = SearchUtil.setDefaultAsterisk(circularNumber);
		circularDetail = SearchUtil.setDefaultAsterisk(circularDetail);
		departmant = SearchUtil.setDefaultAsterisk(departmant);
		fileName = SearchUtil.setDefaultAsterisk(fileName);
		List<CircularDto> circularDtos = circularRepo.findByCircularNumberAndCircularDetailAndDepartmantAndFileNameAndCreatedBy(circularNumber, circularDetail, departmant, fileName,createdBy,new PageRequest(0, 500));
		return circularDtos.stream().filter(x -> x.getClientNumber().equals(clientName)).collect(Collectors.toList()); 
	}
	
}
