package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.EcomCartEntity;
import com.entity.UserEntity;

public interface CartRepository extends JpaRepository<EcomCartEntity, Integer> 
{
	EcomCartEntity findByUser(UserEntity user);
}
