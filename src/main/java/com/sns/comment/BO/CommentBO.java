package com.sns.comment.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.Dao.CommnetMapper;
import com.sns.comment.Model.Comment;
import com.sns.comment.Model.CommentView;
import com.sns.user.BO.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommnetMapper commentMapper;
	@Autowired
	private UserBO userBO;
	
	public int addCommetn(int postId, int userId, String comment) {
		return commentMapper.insertComment(postId, userId, comment);
	}
	
	public List<Comment> getCommentByUserIdPostId(){
		return commentMapper.selectCommentByUserIdPostId();
	}
	public void deleteComment(int id) {
		commentMapper.deleteComment(id);
	}
	// input: postId	output: 가공된 댓글 리스트
	public List<CommentView> generateCommentViewList(int postId) {
		// 결과 리스트
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 글에 해당하는 댓글들
		// List<Commnet> => List<CommentView>
		List<Comment> commentList= new ArrayList<>();
		commentList = commentMapper.generateCommentViewList(postId);
		
		// 반복문 Comment => CommentView => 결과리스트에 담는다.
		for (Comment comment: commentList) {
			CommentView commentView = new CommentView();
			
			// 코멘트뷰에 코멘트를 넣어준다
			commentView.setComment(comment);
			// 댓글쓴 유저를 가져온다
			User user = userBO.getUserByid(comment.getUserId());
			commentView.setUser(user);
			
			// !! 코멘트뷰리스트에 코멘트뷰 추가!
			commentViewList.add(commentView);
		}
		
		return commentViewList;
	}
}
