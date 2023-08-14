package com.jdbc.demo.jpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdbc.demo.model.JPAModel;

@Repository
public interface JPARepository extends JpaRepository<JPAModel, String> {

}
