package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.BO.TimelineBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@Controller
public class timelineController {
	
	@Autowired
	private TimelineBO timelineBO;
	
	@RequestMapping("/timeline_view")
	public String timelineView(Model model, HttpSession session) {
		
		// userId 세션에서 가져오기
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 유효성 검사 로그인상태인지 비 로그인시 로그인 화면으로 이동
		/*
		 * if (userId == null) { return "redirect:/user/sign_in_view"; }
		 */
		
		List<CardView> cardList = timelineBO.generateCardList(userId);
		
		model.addAttribute("cardList", cardList);
		
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
}
