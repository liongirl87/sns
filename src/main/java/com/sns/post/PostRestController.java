package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.BO.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("writeTextArea") String writeTextArea,
			@RequestParam(value="file", required=false) MultipartFile file,
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		
		// 세션에서 유저id 꺼내옴
		int userId = (int)session.getAttribute("userId");
		String loginId = (String)session.getAttribute("loginId");
		
		//db insert
		int countResult = postBO.addPost(loginId, userId, writeTextArea, file);
		//응답
		if (countResult > 0 ) {
			result.put("code", 1);
			result.put("result", "입력성공");
		} else {
			result.put("code", 400);
			result.put("result", "입력실패");
		}
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session){
		
		// 세션에서 유저 아이디 가져오기
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		int countRow = postBO.deletePost(postId, userId);
		
		if (countRow > 0) {
			result.put("code", 1);
			result.put("result", "포스트 삭제에 성공하였습니다");
		} else {
			result.put("code", 400);
			result.put("errorMessage", "포스트 삭제에 실패하였습니다");
		}
		
		return result;
	}
}
