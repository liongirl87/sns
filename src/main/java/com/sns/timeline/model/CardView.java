package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.Model.CommentView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

// view용 객체
// 글 1개와 매핑됨

public class CardView {
	// 글 1개
	private Post post;
	
	// 글쓴이 정보
	private User user;

	// 댓글들
	private List<CommentView> commentList;
	
	// 좋아요 n 개
	private int likeCount;
	
	// 내가(로그인 된 사람) 좋아요 했는지 여부 boolean
	private boolean filledLike;
	
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}

	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	
	
	
}
