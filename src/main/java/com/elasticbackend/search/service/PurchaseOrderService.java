package com.elasticbackend.search.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		purchaseOrderRepo.save(purchaseOrderDto);
	}

}