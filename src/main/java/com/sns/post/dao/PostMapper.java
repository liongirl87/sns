package com.sns.post.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostMapper {

	public int insertPost(
			@Param("userId") int userId,
			@Param("content") String writeTextArea,
			@Param("imagePath") String imagePath);
	
	public List<Post> selectPost();
	
	public int deletePost(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public Post selectPostByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
}

