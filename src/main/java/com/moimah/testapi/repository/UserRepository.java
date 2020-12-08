package com.moimah.testapi.repository;


import com.moimah.testapi.entity.Tb01User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * JPA Repository to User
 */
public interface UserRepository extends JpaRepository<Tb01User, Long>, QuerydslPredicateExecutor<Tb01User> {
}
