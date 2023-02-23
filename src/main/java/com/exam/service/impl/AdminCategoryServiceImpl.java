package com.exam.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Category;
import com.exam.repo.AdminCategoryRepository;
import com.exam.service.AdminCategoryService;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

	@Autowired
	private AdminCategoryRepository adminCategoryRepository;

	@Override
	public Category addCategory(Category category) {
		return this.adminCategoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return this.adminCategoryRepository.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		return new LinkedHashSet<>(this.adminCategoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		return this.adminCategoryRepository.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category category = new Category();
		category.setCid(categoryId);
		this.adminCategoryRepository.delete(category);
	}
}
