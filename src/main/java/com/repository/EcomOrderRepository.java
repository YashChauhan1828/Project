package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.EcomOrderEntity;
import com.entity.UserEntity;

public interface EcomOrderRepository extends JpaRepository<EcomOrderEntity, Integer> 
{
	List<EcomOrderEntity> findByUser(UserEntity user); 
}
