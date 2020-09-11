package com.elasticbackend.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elasticbackend.search.dto.CatalogDto;
import com.elasticbackend.search.service.CatalogService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/App")
public class CatalogController {

	@Autowired
	private CatalogService catalogService;

	@GetMapping("/Catalog")
	public Iterable<CatalogDto>	getAllCatalog(){
		return catalogService.getAllCatalog();
	}

	@PostMapping("/Catalog")
	public void	saveCatalogs(@RequestBody CatalogDto catalogDto){
		catalogService.saveCatalogs(catalogDto);
	}
	
	@GetMapping("/Duplicate-Catalog/{modelNo}")
	public Boolean checkDuplicateCircularNumber(@PathVariable("modelNo")String modelNo) {
		return catalogService.checkDuplicateModelNo(modelNo);
	}


}


