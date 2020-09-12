package com.elasticbackend.search.service;

import java.util.List;
import java.util.Optional;

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


	public Boolean checkDuplicateModelNo(String modelNo) {
		Boolean flag = false;
		Optional<List<CatalogDto>> option = catalogRepo.findByModelNo(modelNo);
		if(option.isPresent()) {
			List<CatalogDto> catalogDtos = option.get();
			Long count = catalogDtos.stream().filter(x->x.getModelNo().equalsIgnoreCase(modelNo)).count();
			if(count > 0) {
				flag = true;
			}
		}
		return flag;
	}

	public List<CatalogDto> getMatchingCatalog(String key){
		return catalogRepo.findByProductNameOrModelNoOrOldModelNoOrVoltageOrRangeOrColourOrFileName
				(key, key, key, key, key, key, key);
	}

}
