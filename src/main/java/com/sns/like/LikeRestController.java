package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.BO.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	
	// 	GET: /like?postId=13		@RequestParam
	//  GET or POST: /like/13		@PathVariable
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if( userId == null) {
			// 로그인이 안될 경우 에러메세지 출력
			result.put("code", 300);
			result.put("errorMessage", "로그인을 해주세요");
			return result;
		} 
		likeBO.checkLike(postId, userId);
		
		result.put("code",1);
		result.put("resutl", "success");
		
		// BO 호출 => BO안에서 like 여부 체크
		// 응답
		return result;
	}
}
