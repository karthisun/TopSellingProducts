package com.karthisun.tspcfs.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TopSellingProducts10k DTO")
public class ProductCategoryDTO {

	private int category_id;
	private int product_id;
	private int count;

}