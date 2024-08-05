package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.entities.Category;
import com.blog.entities.Comment;
import com.blog.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {
	
	private Integer postId;
	private String title;
	private String content;
	
	private String imageName;
	private Date addedDate;
	
	
	// we take CategoryDto and userDto here --> bcso it will go under infinite loop it we take Category entity here 
	private CategoryDto category;
	private UserDto user;
	
	
	private Set<CommentDto> comments = new HashSet<>();
	
}
