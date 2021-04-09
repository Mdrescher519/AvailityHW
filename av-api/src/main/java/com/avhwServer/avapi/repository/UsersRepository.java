package com.avhwServer.avapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.avhwServer.avapi.entity.Users;

@RepositoryRestResource()
public interface UsersRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users>, QuerydslPredicateExecutor<Users> {}
