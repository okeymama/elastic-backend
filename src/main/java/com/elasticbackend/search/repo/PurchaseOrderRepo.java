package com.elasticbackend.search.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticbackend.search.dto.PurchaseOrderDto;

@Repository
public interface PurchaseOrderRepo extends ElasticsearchRepository<PurchaseOrderDto, String> {

	Optional<List<PurchaseOrderDto>> findByItemCode(String itemCode);

	List<PurchaseOrderDto> findByOrderNoOrItemOrMakeOrModelNoOrQuantityOrRateOrRemarkOrItemCodeOrCustomerOrFileName(String orderNo,String item,String make,String modelNo,String quantity,String rate,String remark,String itemCode,String customer,String fileName);

}
