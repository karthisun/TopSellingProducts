package com.karthisun.tspcfs.cassandra.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.karthisun.tspcfs.cassandra.model.SellingProducts;
import com.karthisun.tspcfs.dto.ProductCategoryDTO;

public interface SellingProductsRepo extends CrudRepository<SellingProducts, UUID> {

	@Query("select * from soldproducts where category_id = ?0 ALLOW FILTERING")
	List<SellingProducts> findByCategoryId(int category_id);
	
	
}