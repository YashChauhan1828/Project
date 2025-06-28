package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	Integer cartitemId;	
	Integer qty;
	

	@ManyToOne
	@JoinColumn(name = "cartId")
	@JsonIgnore
	EcomCartEntity cart;

	@ManyToOne
	@JoinColumn(name = "product_id")
	EcomProductEntity product;
	
	
}
