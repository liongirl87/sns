package com.sns.like.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.Dao.LikeMapper;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
	
	public void checkLike(int postId, int userId) {
		
		// 좋아요 있는지확인
		int likeCount = likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
		
		if (likeCount > 0) {
			// 있으면 제거 delete
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// 없으면 추가 insert
			likeMapper.insertLikeByPostIdUserId(postId, userId);
		}
		
		
	}
	
	public boolean checkLikeReturnBoolean(int postId, Integer userId) {
		
		//불린위해 숫자체크
		// 비로그인 경우
		if (userId == null) {
			return false;
		}
		
		// 로그인
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;

	}
	public int likeCount(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
}
