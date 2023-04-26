package com.sns.comment.Model;

import com.sns.user.model.User;

// 댓글 하나
public class CommentView {
	// 댓글 하나
	private Comment comment;
	
	// 댓글쓴이
	private User user;
	

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
