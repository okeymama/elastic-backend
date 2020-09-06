/**
 * 
 */
package com.elasticbackend.search.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.CircularDto;



/**
 * @author cchaubey
 *
 */
@Repository
public interface CircularRepo extends ElasticsearchRepository<CircularDto, Long>{

	List<CircularDto> findByCircularNumberOrDateOrCircularDetailOrDepartmantOrFileName(String circularNumber,String date,String circularDetail,String departmant, String fileName);

	Optional<List<CircularDto>> findByClientNumberAndCircularNumber(String clientName, String circularNumber);
	
}
		