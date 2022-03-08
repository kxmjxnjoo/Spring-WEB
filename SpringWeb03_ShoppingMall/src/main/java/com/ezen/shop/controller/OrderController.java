package com.ezen.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
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
	
	
	
	
	
	
	@RequestMapping("orderAll") //총 주문내역
	public ModelAndView orderAll(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo ==null) mav.setViewName("member/login");
		else { 
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			List<Integer> oseqList = os.oseqListAll(mvo.getUserid()); //주문번호들 조회
			for(int oseq : oseqList) {
				List<OrderVO> orderListAll = os.listOrderByOseq(oseq) ; //주문번호로 주문상품 조회
				OrderVO ovo = orderListAll.get(0); //상품 중 첫번쨰 추출
				ovo.setPname(ovo.getPname() + " 포함" + orderListAll.size() + " 건"); //상품명 변경
				int totalPrice = 0;
				for (OrderVO ovop : orderListAll)
					totalPrice += ovop.getPrice2() * ovop.getQuantity();
				ovo.setPrice2(totalPrice); //가격 변경
				orderList.add(ovo); //리스트에 추가
			}
			mav.addObject("title", "총 주문 내역");
			mav.addObject("orderList", orderList);
			mav.setViewName("mypage/mypage");
		}
		return mav;
	}
	
	
	
	@RequestMapping("/orderDetail")
	public ModelAndView orderDetail(HttpServletRequest request, @RequestParam("oseq") int oseq) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) mav.setViewName("member/login");
		else {
			List<OrderVO> orderList = os.listOrderByOseq(oseq); //주문번호로 주문 상품들의 리스트 리턴
			int totalPrice = 0;
			for(OrderVO ovo : orderList)
				totalPrice += ovo.getPrice2() + ovo.getQuantity();
			mav.addObject("orderList", orderList);
			mav.addObject("totalPrice", totalPrice);
			mav.setViewName("mypage/orderDetail");
			mav.addObject("orderDetail", orderList.get(0));
		}
		return mav;
	}
}
