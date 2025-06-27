package com.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ecomreviews")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomReviewEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer reviewIdId;
	Integer rating;
	String comment;
	LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	UserEntity user;
	
	@ManyToOne
	@JoinColumn(name ="product_id")
	EcomProductEntity product;
	
}
