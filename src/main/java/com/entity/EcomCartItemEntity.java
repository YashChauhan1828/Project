package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ecomcartitem")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomCartItemEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cartId;	
	Integer qty;
	@ManyToOne
	@JoinColumn(name = "user_id")
	UserEntity user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	EcomProductEntity product;
	

		
}
