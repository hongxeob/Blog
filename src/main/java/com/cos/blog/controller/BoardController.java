package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" })
	public String index(Model model) {
		model.addAttribute("boards", boardService.글목록());

		return "index"; //viewResolver가 작동! 앞뒤로 프리픽스/서픽스를 붙혀준다.
	}

	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
