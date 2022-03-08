package com.ezen.shop.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView adminLogin(HttpServletRequest request) {
		
	}
	
	
	
	
	
	
}
