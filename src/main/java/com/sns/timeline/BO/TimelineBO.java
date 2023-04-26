package com.sns.timeline.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.BO.CommentBO;
import com.sns.comment.Model.CommentView;
import com.sns.like.BO.LikeBO;
import com.sns.post.BO.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.BO.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// 비로그인도 카드 리스트가 보여져야 하기 떄문에 userId는 null 가능
	public List<CardView> generateCardList(Integer userId) {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록을 가져온다.
		List<Post> postList = postBO.getPost();
		
		// postList를 하나하나 꺼내서 cardViewList에 넣는다.
		for (Post post : postList) {
			CardView card = new CardView();
			
			// 글
			card.setPost(post);
			
			// 글쓴이 정보
			User user = userBO.getUserByid(post.getUserId()); 
			card.setUser(user);
			
			// 댓글들
			List<CommentView> commentView = commentBO.generateCommentViewList(post.getId());
			card.setCommentList(commentView);

				
			// 내가(로그인된사람) 좋아요를 눌렀는지 여부 // 비로그인은 무조건 빈하트
			card.setFilledLike(likeBO.checkLikeReturnBoolean(post.getId(), userId));

			// postId별 라이크 숫자
			card.setLikeCount(likeBO.likeCount(post.getId()));
			
			// !!!!카드 리스트에 채우기!!!
			cardViewList.add(card);
		}
		
		// postList를 반복문	=> 1:1 post -> CardView -> cardViewList에 넣는다.
		return cardViewList;
	}
}
