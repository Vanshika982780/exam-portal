package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Category;

public interface AdminCategoryRepository extends JpaRepository<Category,Long>{

}
