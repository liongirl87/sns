package com.sns.like.Dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {

//	public int checkLikeByPostIdUserId(
//			@Param("postId") int postId,
//			@Param("userId") int userId);
	
	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void insertLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	public boolean checkLikeReturnBoolean(
			@Param("postId") int postId,
			@Param("userId") int userId);
	// public int likeCount(int postId);
	
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	public void deletePostByPostId(int postId);
}
