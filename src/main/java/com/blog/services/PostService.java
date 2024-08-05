package com.blog.services;

import java.util.List;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {

	// CREATE
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	// UPDATE 
	PostDto updatePost(PostDto postDto, Integer posId);

	// DELETE
	void deletePost(Integer postId);
	
	// Get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	// Get single post
	PostDto getPostById(Integer postId);
	
	
	// Get all posts by category
	List<PostDto> getPostsByCategory(Integer catId);

	
	// Get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	
	// Search post
	List<PostDto> searchPosts(String keywords);
	
}
