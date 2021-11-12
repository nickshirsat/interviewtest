package com.interviewtest.interviewtest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interviewtest.interviewtest.model.ProductModel;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel, UUID>{

}
