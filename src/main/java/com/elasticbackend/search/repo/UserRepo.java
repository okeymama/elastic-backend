package com.elasticbackend.search.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.elasticbackend.search.dto.UserDto;

public interface UserRepo extends ElasticsearchRepository<UserDto, String>{
	
	public UserDto findByUserNameAndPassword(String userName, String password);

}
