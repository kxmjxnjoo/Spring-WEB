package com.ezen.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	QnaService qs;
	
	@RequestMapping("/qnaList")
	public ModelAndView qns_list(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if (mvo ==null)mav.setViewName("member/login");
		else {
			mav.addObject("qnaList", qs.listQna(mvo.getUserid()) );
			mav.setViewName("qna/qnaList");
		}
		return mav;
	}
	
	
	
	
	@RequestMapping("/qnaWriteForm")
	public ModelAndView qna_write_form(Model model, HttpServletRequest request) {
		HttpSession session = requset.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		}
	return "qna/qnaWrite";
}


	@RequestMapping("qnaWrite");
	public ModelAndView qna_write(HttpServletRequest request,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content) {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo ==null)mav.setViewName("member/login");
		else {
			mav.addObject("qnaList", qs.listQna(mvo.getUserid()) );
			mav.setViewName("qna/qnaList");
		}
		
		
		
	@RequestMapping("/qnaView")
	public ModelAndView qna_view( HttpServletRequest request) {
		@RequestParam("qseq") int qseq) {
			ModelAndView mav = new ModelAndView();
			HttpSession session = request.getSession();
		}
	}
}
