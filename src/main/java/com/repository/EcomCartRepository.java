package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.EcomCartEntity;
import com.entity.EcomCartItemEntity;
import com.entity.UserEntity;

public interface EcomCartRepository extends JpaRepository<EcomCartItemEntity, Integer>
{
	List<EcomCartItemEntity> findByCart(EcomCartEntity cart);
	EcomCartItemEntity findByCartitemId(Integer cartitemId);
//	List<EcomCartItemEntity> findByCartitemId(Integer cartitemId);
	EcomCartItemEntity save(Integer qty);
}
