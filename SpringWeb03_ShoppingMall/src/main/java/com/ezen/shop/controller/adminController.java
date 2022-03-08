package com.ezen.shop.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.service.AdminService;
import com.ezen.shop.service.ProductService;
import com.ezen.shop.service.QnaService;

@Controller
public class adminController {

	@Autowired
	AdminService as;
	
	@Autowired
	ProductService ps;
	
	@Autowired
	QnaService qs;
	
	@Autowired
	ServletContext context;
	//관리자는 다양하게 접근해서 사용하기 때문에 다양하게 필요함
	
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin/adminLoginForm";
	}
	
	

	@RequestMapping("adminLogin")
	public ModelAndView adminLogin( HttpServletRequest request,
			@RequestParam("workId") String workId,
			@RequestParam("workPwd") String workPwd) {
		
		ModelAndView mav = new ModelAndView();
		
		int result = as.workerCheck(workId, workPwd);
		// result 값이 1이면 정상 로그인, 0 이면 비밀번호 오류, -1 이면 아이디 없음
		
		if(result == 1) {
			HttpSession session = request.getSession();
    		session.setAttribute("workId", workId);
    		mav.setViewName("redirect:/productList");
		}else if( result == 0) {
			mav.addObject("message" , "비밀번호를 확인하세요");
			mav.setViewName("admin/adminLoginForm");
		}else if(result == -1) {
			mav.addObject("message" , "아이디를 확인하세요");
			mav.setViewName("admin/adminLoginForm");
		}else {
			mav.addObject("message" , "이유를 알수없지만 로그인 안돼요");
			mav.setViewName("admin/adminLoginForm");
		}
		return mav;
	}
	
	
	
	@RequestMapping("/productList")
	public String product_list(HttpServletRequest request) {
		
		return "admin/product/productList";
	}
}