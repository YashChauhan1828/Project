package com.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.UserEntity;
import java.util.List;
import java.util.Optional;


public interface EcomUserRepository extends JpaRepository<UserEntity, Integer>
{
	UserEntity findByEmailAndPassword(String email, String password); 
	UserEntity findByEmail(String email);
	Optional<UserEntity>  findByToken(String token);
}
