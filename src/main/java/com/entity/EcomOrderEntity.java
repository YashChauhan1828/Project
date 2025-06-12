package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ecomorders")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomOrderEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer orderId;
	Integer qty;
	Integer price;
	String productName;
	String category;
	String productImagePath;
	
}
