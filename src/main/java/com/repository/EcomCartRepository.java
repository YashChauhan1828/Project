package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.entity.EcomCartItemEntity;

public interface EcomCartRepository extends JpaRepository<EcomCartItemEntity, Integer>
{
	
}
