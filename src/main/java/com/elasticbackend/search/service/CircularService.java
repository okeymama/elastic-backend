package com.elasticbackend.search.service;

import java.util.ArrayList;
import java.util.List;

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

	public CircularDto getCircular(Long circularId) {
		CircularDto circularDto = null;
		if(circularRepo.findById(circularId).isPresent()) {
			circularDto = circularRepo.findById(circularId).get();
		}
		return circularDto ;
	}

	/*
	 * public List<CircularDto> getCircularMatch(String key) { NativeSearchQuery
	 * searchQuery = new NativeSearchQueryBuilder()
	 * .withQuery(multiMatchQuery("tutorial") .field("title") .field("tags")
	 * .type(MultiMatchQueryBuilder.Type.BEST_FIELDS)) .build(); circularDtos =
	 * circularRepo.search(searchQuery); return circularDtos ; }
	 */
	
	
	public Page<CircularDto> getCircularMatch(String keyWord, int page, int limit) {
		Pageable pageable = PageRequest.of(page, limit);
		QueryBuilder queryBuilder = //QueryBuilders.boolQuery()
				//.should(QueryBuilders.matchQuery("clientNumber", keyWord))
				//.should(QueryBuilders.matchQuery("circularNumber", keyWord))
				//.should(QueryBuilders.matchQuery("date", keyWord))
				//.should(QueryBuilders.matchQuery("circularDetail", keyWord))
				//.should(QueryBuilders.matchQuery("departmant", keyWord));
		QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(keyWord).analyzeWildcard(true)
        //.field("clientNumber").field("circularNumber").field("date")
        .field("circularDetail"));
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		nativeSearchQueryBuilder.withQuery(queryBuilder).withPageable(pageable);
		NativeSearchQuery query = nativeSearchQueryBuilder.build();
		return circularRepo.search(query);
	}
	
	
	public List<CircularDto> getCircularMatch(String keyWord){
		return circularRepo.findByCircularNumberOrDateOrCircularDetailOrDepartmant(keyWord, keyWord, keyWord, keyWord);
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
