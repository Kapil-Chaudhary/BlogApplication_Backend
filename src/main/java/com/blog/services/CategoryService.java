package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;


public interface CategoryService {

	// CREATE
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	// UPDATE
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	
	// DELETE
	public void deleteCategory(Integer caInteger);
	
	// GET
	public CategoryDto getCategory(Integer categoryId);
	
	// GET ALL
	List<CategoryDto> getCategories();
		
}
