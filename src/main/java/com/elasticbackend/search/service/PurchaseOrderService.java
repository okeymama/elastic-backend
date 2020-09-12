package com.elasticbackend.search.service;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.elasticbackend.search.dto.PurchaseOrderDto;
import com.elasticbackend.search.repo.PurchaseOrderRepo;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepo purchaseOrderRepo;

	public Iterable<PurchaseOrderDto> getAllPurchaseOrder(){
		Iterable<PurchaseOrderDto> purchaseOrderDtos = purchaseOrderRepo.findAll();
		return purchaseOrderDtos;
	}

	public void savePurchaseOrderOrders(PurchaseOrderDto purchaseOrderDto) {
		if(null != purchaseOrderDto && null != purchaseOrderDto.getDate()) {
			purchaseOrderDto.setRemark(purchaseOrderDto.getRemark()+" >> "+purchaseOrderDto.getDate());
		}
		purchaseOrderRepo.save(purchaseOrderDto);
	}

	public Boolean checkDuplicatePOItemCode(String itemCode) {
		Boolean flag = false;
		Optional<List<PurchaseOrderDto>> purchaseOrderDtoOption = purchaseOrderRepo.findByItemCode(itemCode);
		if(purchaseOrderDtoOption.isPresent()) {
			List<PurchaseOrderDto> purchaseOrderDtos = purchaseOrderDtoOption.get();
			Long count = purchaseOrderDtos.stream().filter(cd->cd.getItemCode().equals(itemCode)).count();
			if(count>0) {
				flag = true;
			}
		}
		return flag ;
	}

	public Page<PurchaseOrderDto> getPurchaseOrderMatch(String key,int page, int limit) {
		Pageable pageable = PageRequest.of(page, limit);
		QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(key).analyzeWildcard(true)
				.field("orderNo")
				.field("item")
				.field("make")
				.field("quantity")
				.field("rate")
				.field("remark")
				.field("date")
				.field("itemCode")
				.field("customer")
				.field("fileName"));
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		nativeSearchQueryBuilder.withQuery(queryBuilder).withPageable(pageable);
		NativeSearchQuery query = nativeSearchQueryBuilder.build();
		return purchaseOrderRepo.search(query);
	}
	
	
	public List<PurchaseOrderDto> getMatchingPurchaseOrder(String key){
		return purchaseOrderRepo.findByOrderNoOrItemOrMakeOrModelNoOrQuantityOrRateOrRemarkOrItemCodeOrCustomerOrFileName
				(key, key, key, key, key, key, key, key, key, key);
	}
	
	


}