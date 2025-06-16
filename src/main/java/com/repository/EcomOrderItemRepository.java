package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.EcomOrderEntity;
import com.entity.EcomOrderItemEntity;

public interface EcomOrderItemRepository extends JpaRepository<EcomOrderItemEntity, Integer>
{
	List<EcomOrderItemEntity> findByOrder(EcomOrderEntity order);
//	EcomOrderItemEntity findByOrderItemId(Integer orderitemId);
}
