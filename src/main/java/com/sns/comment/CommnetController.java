package com.sns.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.comment.BO.CommentBO;

@RequestMapping("/comment")
@Controller
public class CommnetController {

	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/delete")
	public String commentDelete(@RequestParam("id") int id) {
		commentBO.deleteComment(id);
		return "redirect:/timeline/timeline_view";
	}
}
