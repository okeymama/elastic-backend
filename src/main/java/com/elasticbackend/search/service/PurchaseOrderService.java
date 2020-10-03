package com.elasticbackend.search.service;

import java.util.Date;
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
import com.elasticbackend.search.util.SearchUtil;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepo purchaseOrderRepo;

	public Iterable<PurchaseOrderDto> getAllPurchaseOrder(){
		Iterable<PurchaseOrderDto> purchaseOrderDtos = purchaseOrderRepo.findAll(new PageRequest(0, 500));
		return purchaseOrderDtos;
	}

	public void savePurchaseOrderOrders(PurchaseOrderDto purchaseOrderDto) {
		if(null != purchaseOrderDto && null != purchaseOrderDto.getDate()) {
			purchaseOrderDto.setRemark(purchaseOrderDto.getRemark()+" >> "+purchaseOrderDto.getDate());
		}
		if(null == purchaseOrderDto.getCreationDate()) {
			purchaseOrderDto.setCreationDate(new Date());
		}
		purchaseOrderRepo.save(purchaseOrderDto);
	}

	public Boolean checkDuplicatePOOrderNo(String orderNo) {
		Boolean flag = false;
		Optional<List<PurchaseOrderDto>> purchaseOrderDtoOption = purchaseOrderRepo.findByOrderNo(orderNo);
		if(purchaseOrderDtoOption.isPresent()) {
			List<PurchaseOrderDto> purchaseOrderDtos = purchaseOrderDtoOption.get();
			Long count = purchaseOrderDtos.stream().filter(cd->cd.getOrderNo().equals(orderNo)).count();
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
		return purchaseOrderRepo.findByOrderNoOrItemOrMakeOrModelNoOrQuantityOrRateOrRemarkOrItemCodeOrCustomerOrFileNameOrCreatedBy
				(key, key, key, key, key, key, key, key, key, key,key, new PageRequest(0, 500));
	}
	
	public List<PurchaseOrderDto> getMatchingPurchaseOrder(String orderNo,String item,String make,String modelNo,String quantity,String rate,String remark,String itemCode,String customer,String fileName,String createdBy){
		orderNo = SearchUtil.setDefaultAsterisk(orderNo);
		item = SearchUtil.setDefaultAsterisk(item);
		make = SearchUtil.setDefaultAsterisk(make);
		modelNo = SearchUtil.setDefaultAsterisk(modelNo);
		quantity = SearchUtil.setDefaultAsterisk(quantity);
		rate = SearchUtil.setDefaultAsterisk(rate);
		remark = SearchUtil.setDefaultAsterisk(remark);
		itemCode = SearchUtil.setDefaultAsterisk(itemCode);
		customer = SearchUtil.setDefaultAsterisk(customer);
		fileName = SearchUtil.setDefaultAsterisk(fileName);
		return purchaseOrderRepo.findByOrderNoAndItemAndMakeAndModelNoAndQuantityAndRateAndRemarkAndItemCodeAndCustomerAndFileNameAndCreatedBy
				(orderNo, item, make, modelNo, quantity, rate, remark, itemCode, customer, fileName, createdBy,new PageRequest(0, 500));
	}


}