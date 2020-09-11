package com.elasticbackend.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elasticbackend.search.dto.CircularDto;
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
	public void	savePurchaseOrders(@RequestBody PurchaseOrderDto PurchaseOrderDto){
		purchaseOrderService.savePurchaseOrderOrders(PurchaseOrderDto);

	}

	@GetMapping("/Duplicate-PurchaseOrder/{item}/{iteamCode}")
	public Boolean checkDuplicatePOIteamCode(@PathVariable("item")String item,@PathVariable("iteamCode")String iteamCode){
		return purchaseOrderService.checkDuplicatePOItemCode(iteamCode);
	}


	@GetMapping("/PurchaseOrder/{key:.+}/{page}/{limit}")
	public @ResponseBody ResponseEntity<Page<PurchaseOrderDto>> getCircularMatch(@PathVariable("key") String key,@PathVariable("page") int page,@PathVariable("limit") int limit){
		Page<PurchaseOrderDto> purchaseOrderDtos = purchaseOrderService.getPurchaseOrderMatch(key,page,limit);
		 return ResponseEntity.ok(purchaseOrderDtos);
	}
	
}
