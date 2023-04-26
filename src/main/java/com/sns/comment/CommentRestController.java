package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.BO.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/create")
	public Map<String, Object> createComment(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String comment,
			HttpSession session,
			Model model) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		
		int resultCount = commentBO.addCommetn(postId, userId, comment);
		if(resultCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 400);
			result.put("result", "실패");
		}
		
		
		
		return result;
	}
	
}
