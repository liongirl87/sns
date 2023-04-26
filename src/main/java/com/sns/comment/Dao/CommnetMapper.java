package com.sns.comment.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.Model.Comment;

@Repository
public interface CommnetMapper {

	public int insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("comment") String comment);
	
	public List<Comment> selectCommentByUserIdPostId();
	
	public void deleteComment (int id);
	
	public List<Comment> generateCommentViewList(int postId);
	
	public void deleteCommentByPostId (int postId);
}
