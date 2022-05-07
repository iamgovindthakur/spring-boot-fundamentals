package com.iamgovindthakur.dualdatasource.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamgovindthakur.dualdatasource.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
