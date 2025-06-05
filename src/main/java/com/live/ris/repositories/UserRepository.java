package com.live.ris.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.live.ris.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String userName);

	List<User> findByFullNameContainingIgnoreCaseOrPhoneContaining(String keyword, String keyword2);
    
    
}
