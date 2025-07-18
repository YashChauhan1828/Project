package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ecomwishcart")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomWishList 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer wishcartId;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	UserEntity user;

	@OneToMany(mappedBy = "wishcart")
	List<EcomWishCartItemEntity> wishitems;
}
