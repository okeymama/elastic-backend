package com.elasticbackend.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elasticbackend.search.dto.PurchaseOrderDto;
import com.elasticbackend.search.service.PurchaseOrderService;



@CrossOrigin(value="*")
@RequestMapping("/App")
@RestController
public class PurchaseOrderController {


	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@GetMapping("/PurchaseOrders")
	public Iterable<PurchaseOrderDto> getAllPurchaseOrder(){
		return purchaseOrderService.getAllPurchaseOrder();
	}

	@PostMapping("/PurchaseOrders")
	public void	savePurchaseOrders(@RequestBody PurchaseOrderDto purchaseOrderDto){
		purchaseOrderService.savePurchaseOrderOrders(purchaseOrderDto);

	}

	@GetMapping("/Duplicate-PurchaseOrder/{orderNo}")
	public Boolean checkDuplicatePOIteamCode(@PathVariable("orderNo")String orderNo){
		return purchaseOrderService.checkDuplicatePOOrderNo(orderNo);
	}

	@GetMapping("/PurchaseOrders/{key}")
	public List<PurchaseOrderDto> getMatchingPurchaseOrder(@PathVariable("key")String key){
		return purchaseOrderService.getMatchingPurchaseOrder(key);
	}

	@PostMapping("/PurchaseOrders-Match")
	public List<PurchaseOrderDto> getMatchingPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto){
		return purchaseOrderService.getMatchingPurchaseOrder(purchaseOrderDto.getOrderNo(), purchaseOrderDto.getItem(), purchaseOrderDto.getMake(), purchaseOrderDto.getModelNo(), purchaseOrderDto.getQuantity(), purchaseOrderDto.getRate(), purchaseOrderDto.getRemark(), purchaseOrderDto.getItemCode(), purchaseOrderDto.getCustomer(), purchaseOrderDto.getFileName(),purchaseOrderDto.getCreatedBy());
	}
	
}
