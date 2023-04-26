package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/sign_up_view")
	public String signUpView (Model model) {
		model.addAttribute("view", "user/signUp");
		return "template/layout";
	}
	
	@GetMapping("/sign_in_view")
	public String signInView (Model model) {
		model.addAttribute("view", "user/signIn");
		return "template/layout";
	}
	
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
		// 세션에 있는 모든 것을 비운다.
		session.removeAttribute("userId");
		session.removeAttribute("name");
		session.removeAttribute("loginId");
		
		// 화면 이동(로그인화면)
		return "redirect:/timeline/timeline_view";
	}
	
	
}
