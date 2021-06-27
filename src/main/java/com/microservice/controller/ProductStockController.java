package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.beans.ProductStockBean;
import com.microservice.dao.ProductStockRepository;
import com.microservice.entity.ProductStock;

@RestController
public class ProductStockController {
	
	@Autowired
	ProductStockRepository productStockRepository;
	
	@Autowired
	Environment environment;
	
	@PostMapping("/saveData")
	public void saveData(@RequestBody ProductStock ps)
	{
		productStockRepository.save(ps);
	}
	
	@GetMapping("/check-product-stock/productName/{productName}/productAvailability/{productAvailability}")
	public ProductStockBean checkProductStock(@PathVariable String productName,
	                                          @PathVariable String productAvailability)
	{

	    ProductStock productStock=productStockRepository.findByProductNameAndProductAvailability(productName,productAvailability);

	    ProductStockBean productStockBean=new ProductStockBean(
	            productStock.getId(),
	            productStock.getProductName(),
	            productStock.getProductPrice(),
	            productStock.getProductAvailability(),
	            productStock.getDiscountOffer(),
	            Integer.parseInt(environment.getProperty("local.server.port"))
	    );

	    return productStockBean;
	}
	
	@GetMapping("/getData/{id}")
	public ProductStock getProduct(@PathVariable Long id) {
		
		return productStockRepository.getById(id);
		
	}
	

}
