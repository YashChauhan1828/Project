package com.entity;

import java.sql.Date;
import java.util.List;

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
@Table(name = "ecomorders")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomOrderEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer orderId;

	
	
	@ManyToOne
	@JoinColumn( name = "user_id")
	UserEntity user;
	
	@OneToMany(mappedBy = "order")
	List<EcomOrderItemEntity> orderitems;
}
