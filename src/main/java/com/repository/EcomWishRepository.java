package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.EcomWishList;
import com.entity.UserEntity;

public interface EcomWishRepository extends JpaRepository<EcomWishList, Integer>  
{
	EcomWishList findByUser(UserEntity user);
}
