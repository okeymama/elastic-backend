/**
 * 
 */
package com.elasticbackend.search.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.CircularDto;



/**
 * @author cchaubey
 *
 */
@Repository
public interface CircularRepo extends ElasticsearchRepository<CircularDto, Long>{

	List<CircularDto> findByCircularNumberOrCircularDetailOrDepartmantOrFileNameOrCreatedBy(String circularNumber,String circularDetail,String departmant, String fileName, String createdBy,PageRequest pageRequest);

	Optional<List<CircularDto>> findByClientNumberAndCircularNumber(String clientName, String circularNumber);
	
	@Query("")
	List<CircularDto> findAllCircular();
	
	@Query("")
	Long findAllCircularCount();
	
	@Query("")
	Long findCircularCountByUser(@Param("createdBy") String createdBy);

	Long countByCreatedBy(String createdBy);

	List<CircularDto> findByCircularNumberAndCircularDetailAndDepartmantAndFileNameAndCreatedBy(String circularNumber,
			String circularDetail, String departmant, String fileName,String createdBy,PageRequest pageRequest);
	
}
		