package com.karthisun.tspcfs.cassandra.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.karthisun.tspcfs.cassandra.model.TopSellingProducts10k;

@Repository
public interface Top10kProductDetailsRepo extends CrudRepository<TopSellingProducts10k, UUID> {

	@Override
    List<TopSellingProducts10k> findAll();
	
	
}