package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.EcomShippingEntity;
import com.entity.UserEntity;

public interface EcomShippingRepository extends JpaRepository<EcomShippingEntity, Integer>  
{
	@Query(value = "SELECT * FROM ecomshippingdetails WHERE user_id = ? ORDER BY shipping_id DESC LIMIT 1", nativeQuery = true)
	EcomShippingEntity findLatestShippingForUser(Integer userId);
}
