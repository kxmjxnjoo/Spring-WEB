package com.ezen.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.service.CartService;
import com.ezen.shop.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService os;
	
	@Autowired
	CartService cs; 
	//주문에서 카트를 사용하는 일이 있기 때문에 가져옴 
	
	@RequestMapping("/orederInsert")
	public String orderinsert(HttpServletRequest request) {
		int oseq = 0;
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo==null) {
			return "member/login";
		} else {
			List<CartVO> cartList = cs.listCart(mvo.getUserid());
			os.insertOrder(cartList, mvo.getUserid());
		}
		
		
		//방금 주문한 주문번호로 리스트 조회하여 화면에 표시하러 이동함
		return "redirect:/orderList?oseq=" + oseq;
	}
	
}
