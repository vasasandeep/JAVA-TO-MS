package com.olx.masterdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.masterdata.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
