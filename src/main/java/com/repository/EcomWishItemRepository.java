package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.EcomWishCartItemEntity;
import com.entity.EcomWishList;

import java.util.List;


public interface EcomWishItemRepository extends JpaRepository<EcomWishCartItemEntity, Integer> 
{
	List<EcomWishCartItemEntity> findByWishcart(EcomWishList wishcart);
//	EcomWishCartItemEntity  findByWishcartitemId(Integer wishcartitemId);
}
