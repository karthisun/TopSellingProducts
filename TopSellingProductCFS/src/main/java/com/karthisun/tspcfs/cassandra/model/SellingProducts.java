package com.karthisun.tspcfs.cassandra.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("soldproducts")
public class SellingProducts {
	@PrimaryKey
	private int category_id;
	private int product_id;
	private int count;
}
