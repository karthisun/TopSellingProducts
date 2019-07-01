package com.karthisun.tspcfs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karthisun.tspcfs.cassandra.model.SellingProducts;
import com.karthisun.tspcfs.cassandra.model.TopSellingProducts10k;
import com.karthisun.tspcfs.cassandra.repository.SellingProductsRepo;
import com.karthisun.tspcfs.cassandra.repository.Top10kProductDetailsRepo;
import com.karthisun.tspcfs.dto.EmployeeDTO;
import com.karthisun.tspcfs.dto.ProductCategoryDTO;
import com.karthisun.tspcfs.dto.TopSellingDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TopSellingProductsServices {
	
	@Autowired
	private Top10kProductDetailsRepo topSellingRepo;
	
	@Autowired
	private SellingProductsRepo sellingProductsRepo;
	
	public List<TopSellingDTO> getAll10KSold() {
		log.info("Finding all 10K sold");
		List<TopSellingProducts10k> empInDB = topSellingRepo.findAll();
		List<TopSellingDTO> templateDTOList = new ArrayList<>();
		
		for (TopSellingProducts10k template : empInDB) {
			ObjectMapper mapper = new ObjectMapper();
			
			TopSellingDTO templateDTO = mapper.convertValue(template, TopSellingDTO.class);
			templateDTOList.add(templateDTO);
		}
		return templateDTOList;
	}
	
	public List<ProductCategoryDTO> getProductByCategoryID(int categoryID ) {
		List<SellingProducts> empInDB = sellingProductsRepo.findByCategoryId(categoryID);
		List<ProductCategoryDTO> templateDTOList = new ArrayList<>();
		
		for (SellingProducts template : empInDB) {
			ObjectMapper mapper = new ObjectMapper();
			
			ProductCategoryDTO templateDTO = mapper.convertValue(template, ProductCategoryDTO.class);
			templateDTOList.add(templateDTO);
		}
		return templateDTOList;
	}
}
