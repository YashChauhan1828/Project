package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.EcomProductEntity;

@Repository
public interface EcomProductRepository extends JpaRepository<EcomProductEntity, Integer>
{
	@Query(value = "SELECT * FROM ecomproducts LIMIT 9", nativeQuery = true)
	List<EcomProductEntity> findTop9();
}
