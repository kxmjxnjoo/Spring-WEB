package com.ezen.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.board.dto.MemberDto;
import com.ezen.board.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String firstRequest( HttpServletRequest request , Model model ) {
		
		HttpSession session = request.getSession();
		String url = "";
		if( session.getAttribute("loginUser") != null )
			url = "redirect:/boardList";
		else 
			url = "member/loginForm";
		
		return url;
	}
	
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login( HttpServletRequest request, Model model ) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String url = "member/loginForm";
		
		MemberDto mdto = ms.getMember(id);  //id 이용하여 회원 조회, 회원의 모든정보를  dto형태로 리턴받음
		
		if( mdto == null ) {  //조회한 아이디가 없는 경우
			model.addAttribute("message" , "아이디가 없습니다");
		} else if( mdto.getPw() == null ) { //DB 오류
			model.addAttribute("message" , "DB 오류, 관리자에게 문의하세요");
		} else if( mdto.getPw().equals( pw) ) { //정상로그인
			url = "redirect:/boardList";
			// "redirect:/리퀘스트이름   -> 리퀘스트이름에 해당하는 매핑으로 이동 
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto);
		} else if( !mdto.getPw().equals( pw) ) { //비밀번호 틀림 틀림
			model.addAttribute("message" , "비밀번호가 틀렸습니다");
		} else {  //알수 없는 이유로 로그인 불가
			model.addAttribute("message" , "알수없는 이유로 로그인이 안됩니다.");
		}
		return url;
	}
	
	
	
	@RequestMapping("/memberJoinForm")
	public String memberJoinForm(  ) {
		return "member/memberJoinForm";
	}
	
	
	
	@RequestMapping("/idcheck")
	public String idcheck( HttpServletRequest request , Model model) {
		
		String id = request.getParameter("id");
		MemberDto mdto = ms.getMember(id);
		if( mdto == null ) {
			model.addAttribute("result" , -1);
		}else {
			model.addAttribute("result" , 1);
		}
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	
	
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public String memberJoin( HttpServletRequest request, Model model ) {
		
		MemberDto mdto = new MemberDto();
		mdto.setUserid(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		mdto.setName(request.getParameter("name"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setEmail(request.getParameter("email"));
		
		int result = ms.insertMember(mdto);
		
		if( result == 1) model.addAttribute("message","회원가입 성공. 로그인하세요");
		else model.addAttribute("message","회원가입 실패. 다음에 다시 시도하세요");
		
		return "member/loginForm";
	}
	
	
	
	@RequestMapping("/memberEditForm")
	public String memberEditForm(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null) 
			return "loginform";
		
		return "member/memberEditForm";
	}
	
	
	
	@RequestMapping(value="/memberEdit", method=RequestMethod.POST)
	public String memberEdit(Model model, HttpServletRequest request) {
		MemberDto mdto = new MemberDto();
		mdto.setUserid(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		mdto.setName(request.getParameter("name"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setEmail(request.getParameter("email"));
		
		int result = ms.updateMember(mdto);
		
		HttpSession session = request.getSession();
		if(result==1) session.setAttribute("loginUser", mdto);
		
		// redirect:/boardList 로 리턴
		return "redirect:/boardList";
	}
	
	
	
	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.invalidate();
		//session.removeAttribute("loginUser");
	
		//return "member/loginform";
		return "redirect:/";
	}
}