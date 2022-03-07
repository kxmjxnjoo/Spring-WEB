package com.ezen.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.board.dto.MemberDto;
import com.ezen.board.service.MemberService;

@Controller
public class MemberController {
	
	
	@Autowired
	MemberService ms;
	
	
	@RequestMapping(value="/")
	public String loginForm(  ) {
		return "member/loginForm";
	}
	
	@RequestMapping(value="/login")
	public String login( HttpServletRequest request ) {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDto mdto = ms.getMember(id);
		
		return "board/main.jsp";
	}
}





