package com.karthisun.tspcfs.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Template DTO")
public class EmployeeDTO {

	private int emp_id;
	private String emp_city;
	private String emp_name;
	private java.math.BigDecimal emp_phone;
	private java.math.BigDecimal emp_sal;
}
