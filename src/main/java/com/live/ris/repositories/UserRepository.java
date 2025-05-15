// 2. Repository Interface
package com.live.ris.repositories;

import com.live.ris.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}