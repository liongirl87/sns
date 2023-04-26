package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.BO.UserBO;
import com.sns.user.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	
	/**
	 * 아이디 중복 확인
	 * @param loginId
	 * @return
	 */
	@GetMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();
		User user = userBO.getUserByLoginId(loginId);
		
		if (user != null) {
			result.put("code", 1);
			result.put("result", true);
		} else {
			result.put("code", 400);
			result.put("result", false);
		}
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		Map<String, Object> result = new HashMap<>();
		

		// 1. 비밀번호 해싱 MD-5
		String hashedPassword = EncryptUtils.md5(password);
		
		// 2. insert ->BO->DAO->Mapper
		int countRow = userBO.addUser(loginId, hashedPassword, name, email);
		
		// 3. insert 결과로 result Map에 코드 입력
		if(countRow > 0) {
			result.put("code", 1);
		} else {
			result.put("errorMessage", "가입에 실패하였습니다.");
		}
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, Object> userSignIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		Map<String, Object> result = new HashMap<>();
		
		
		
		//암호 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		// select by loginId, password
		User user = userBO.getUserByLoginIdPassword(loginId, hashedPassword);
		
		//세션에 유저정보 담기
		if (user != null) {	//로그인
			result.put("code", 1);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginId", user.getLoginId());
			session.setAttribute("name", user.getName());
			session.setAttribute("userId", user.getId());
		} else {
			result.put("errorMessage", "로그인실패");
		}

		
		return result;
	}
}
