package com.microservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.entity.ProductStock;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
	
	ProductStock findByProductNameAndProductAvailability(String productName, String productAvailability);

}
