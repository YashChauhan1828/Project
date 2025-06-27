package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "ecomproducts")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class EcomProductEntity 
{ 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer productId;
	 String productName;
	 String category;
	 Integer qty;
	 float price;
	 String productImagePath;

	 @OneToMany(mappedBy = "product")
	 @JsonIgnore 
	 List<EcomCartItemEntity> cartItems;
	 
	 @OneToMany(mappedBy = "product")
	 @JsonIgnore
	 List<EcomWishCartItemEntity> wishitems;
	 
	 @OneToMany(mappedBy = "product")
	 @JsonIgnore
	 List<EcomReviewEntity> reviews;
}
