package com.ezen.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService ps;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index( HttpServletRequest request, Model model) {
		
		//List<ProductVO> nlist = ps.getNewList();
		//List<ProductVO> blist = ps.getBestList();
		// List 는 ArrayList 의 부모 객체로 이 프로젝트에서 사용하려고 하는 
		//템플릿(데이터베이스 객체)가 List 만 지원하고 있어서 List 를 사용함
		//model.addAttribute("newProductList", nlist);
		//model.addAttribute("bestProductList", blist);
		
		model.addAttribute("newProductList", ps.getNewList() );
		model.addAttribute("bestProductList", ps.getBestList());
		
		return "index";
	}


	
	@RequestMapping("category")
	public ModelAndView category(/* HttpServletRequest request, Model model */ 
			@RequestParam("kind") String kind) {
		
		ModelAndView mav = new ModelAndView(); //객체생성
		//ModelAndView : model에 addAttribute로 저장할 내용과 이동할 jsp파일의 이름을
		//동시에 저장, 리턴하여 전달값과 이동페이지를 한번에 다룰 수 있게하는 클래스임
		
		//int kind = Integer.parseInt(request.getParameter("kind"));
		//매개변수 위치에 있는 @RequestParam("kind") String kind는
		//변수선언과 동시에 전달되는 파라미터를 받아 저장하는 역활로
		//HttpServletRequest request와 int kind~ 생략이 가능함
		//전달된 파라미터가 10개면 @RequestParam("") 형태로 10개 전부 선언해야함
		//int형 변수도 선언가능함
		
		//단점 1. 문자데이터 전달될 때 정수형 변수 사용불가
		//단점 2. 기본특성상 null값 전달 시 오류발생. 허용하려면 아래와 같이 명시해야함
		// @RequestParam(value="kind", required=false) int kind
		
		//기존방식 1.
		//List<ProductVO> list = ps.getKindList(kind);
		//model.addAttribute("productKindList", list);
		//return "product/productKind";
		
		mav.addObject("productList", ps.getKindList(kind));
		mav.setViewName("product/productKind");
		
		return mav;
	}
	
	
	
	@RequestMapping("/productDetail")
	public ModelAndView productDetail( 
			@RequestParam("pseq") int pseq) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("productVO", ps.getProduct(pseq) );
		mav.setViewName("product/productDetail");
		return mav;
	}
}











