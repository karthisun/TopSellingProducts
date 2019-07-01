package com.karthisun.tspcfs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TopSellingProducts10k DTO")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class TopSellingDTO {

	private int product_id;
	private int count;

}