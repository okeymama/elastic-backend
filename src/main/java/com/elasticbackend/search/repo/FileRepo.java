package com.elasticbackend.search.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.FileDto;

@Repository
public interface FileRepo extends ElasticsearchRepository<FileDto, Long>{

	public FileDto findByName(String fileName);

}
