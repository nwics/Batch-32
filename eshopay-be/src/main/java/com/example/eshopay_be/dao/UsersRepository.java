package com.example.eshopay_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopay_be.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
