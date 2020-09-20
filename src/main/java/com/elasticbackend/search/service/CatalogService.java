package com.elasticbackend.search.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.elasticbackend.search.dto.CatalogDto;
import com.elasticbackend.search.repo.CatalogRepo;

@Service
public class CatalogService {

	@Autowired
	private CatalogRepo catalogRepo;

	public Iterable<CatalogDto>	getAllCatalog() {
		Iterable<CatalogDto> catalogs =	catalogRepo.findAll(new PageRequest(0,500));
		return catalogs;
	}


	public void saveCatalogs(CatalogDto catalogDto) {
		if(null == catalogDto.getCreationDate()) {
			catalogDto.setCreationDate(new Date());
		}
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
				(key, key, key, key, key, key, key, new PageRequest(0, 500));
	}

	public List<CatalogDto> getMatchingCatalog(String productName,String modelNo,String oldModelNo,String voltage,String range,String colour,String fileName){
		return catalogRepo.findByProductNameAndModelNoAndOldModelNoAndVoltageAndRangeAndColourAndFileName
				(productName, modelNo, oldModelNo, voltage, range, colour, fileName, new PageRequest(0, 500));
	}
	
}
