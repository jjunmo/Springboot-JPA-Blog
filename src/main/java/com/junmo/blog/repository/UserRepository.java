package com.junmo.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junmo.blog.model.User;

// DAO
// 자동으로 bean 등록이 됨.
// @Repository // 생략이 가능함.
public interface UserRepository extends JpaRepository<User, Integer>{
		
}
