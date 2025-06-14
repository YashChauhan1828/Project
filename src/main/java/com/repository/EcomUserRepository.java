package com.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.UserEntity;

public interface EcomUserRepository extends JpaRepository<UserEntity, Integer>
{
	UserEntity findByEmailAndPassword(String email , String password);
	UserEntity findByEmail(String email);
}
