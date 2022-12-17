package com.example.stocktrade.repositories;

import com.example.stocktrade.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
