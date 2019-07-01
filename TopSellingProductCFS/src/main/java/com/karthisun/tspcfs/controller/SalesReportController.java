package com.karthisun.tspcfs.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.karthisun.tspcfs.dto.EmployeeDTO;
import com.karthisun.tspcfs.dto.ProductCategoryDTO;
import com.karthisun.tspcfs.dto.TopSellingDTO;
import com.karthisun.tspcfs.service.TopSellingProductsServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@Api(value = "Sales Report services")
public class SalesReportController {
	
	@Autowired
	private TopSellingProductsServices topSellingService;
	
	@Autowired 
	@ApiOperation(
			value = "Get all top 10 K selling products",
			response = TopSellingDTO.class,
			responseContainer = "List")
	@RequestMapping(value = "/getTop10kProducts", method = RequestMethod.GET, produces = "application/json")

	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Request Failed"),
			@ApiResponse(code = 200, message = "Success", response = TopSellingDTO.class),
			@ApiResponse(code = 404, message = "Resource specified in URL is not Found"),
			@ApiResponse(code = 401, message = "Authentication error.")
	})
	public ResponseEntity<?> getAllTopSellingProd() {

		List<TopSellingDTO> templates = topSellingService.getAll10KSold();
		ResponseEntity<?> response = new ResponseEntity<>(templates, HttpStatus.OK);
		return response;
	}
	

	@ApiOperation(value = "Get a Top Selling Products for catagory.", notes = "By giving catagory ID user can view the products", response = ProductCategoryDTO.class)
	@RequestMapping(value = "/getTopSellingProducts/{categoryId}", method = RequestMethod.GET, produces = "application/json")

	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Request Failed"),
			@ApiResponse(code = 200, message = "Success", response = ProductCategoryDTO.class),
			@ApiResponse(code = 404, message = "Resource specified in URL is not Found"),
			@ApiResponse(code = 401, message = "Authentication error.")
	})
	public ResponseEntity<?> getTopSellingProductsByCategoryId(
			@ApiParam(value = "CategoryId to get top selling products is to be fetched") 
			@PathVariable(value = "categoryId", required = true) 
			@NotNull 
			Integer categoryId) 
	{
		List<ProductCategoryDTO> template = topSellingService.getProductByCategoryID( categoryId.intValue() );
		ResponseEntity<?> response;
		if (template != null) {
			response = new ResponseEntity<>(template, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(template, HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
