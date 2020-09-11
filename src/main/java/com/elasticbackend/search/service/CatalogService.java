package com.elasticbackend.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elasticbackend.search.dto.CatalogDto;
import com.elasticbackend.search.repo.CatalogRepo;

@Service
public class CatalogService {

	@Autowired
	private CatalogRepo catalogRepo;

	public Iterable<CatalogDto>	getAllCatalog() {
		Iterable<CatalogDto> catalogs =	catalogRepo.findAll();
		return catalogs;
	}


	public void saveCatalogs(CatalogDto catalogDto) {
		catalogRepo.save(catalogDto);
	}



}
