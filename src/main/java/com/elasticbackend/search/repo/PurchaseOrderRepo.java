package com.elasticbackend.search.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.PurchaseOrderDto;

@Repository
public interface PurchaseOrderRepo extends ElasticsearchRepository<PurchaseOrderDto, String> {

}
