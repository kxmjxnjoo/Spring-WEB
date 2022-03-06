package com.ezen.springmvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
//서버에 요청되는 리퀘스트 들을 다루는 클래스
//클래스의 이름위에 @Controller라는 어노테이션을 표시하면 
//그 안에 사용되는 @RepuestMapping에서 요청리퀘스트가 검색, 선택되이 실행됨
//첫 페이지의 주소 http://localhot:8090/springmvc/는 locahost의 8090포트 중 
//springmvc로 대표되는 프로젝트에 요청을 보낸 상태로, 요청의 키워느는 '/'임
//이는 클래스 안에 있는 메소드들 중 @RequestMapping('/')을 찾아 실행하여 리턴하라는 뜻

@Controller
public class HomeController {
	
	@Autowired
	HomeDao hdao;
	
	@Autowired
	HomeService hs;
	
	//log  출력을 위한 준비
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//method = RequestMethod.Get은 생략가능. POST방식은 반드시 써야함
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date(); //오늘 날짜
		//현재 지역에 맞는 날짜 형식 생성
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		//생성한 형식에 오늘 날짜 적용
		String formattedDate = dateFormat.format(date);
		//request.setAttribute를 대신할 정보 전달 객체 : model , addAttribute로 저장만하면 목적지에 자동 전달됨
		//메소드에 전달인수로 선언하고 사용함
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
		//String 형 return은 
		//servlet-Context.xml에 정의된 경로와 파일 확장자가 조립되어 
		// "/WEB-INF/views/" + "home" + ".jsp" 이 파일을  찾아 웹브라우저에 표시되도록 응답
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/other")
	public String other(Model model) {
		
		HomeDao hdao = new HomeDao(); //dao 객체 생성
		//String message = hdao.getMessage(); //dao에 getMessage method 실행
		String message = hs.getMessage(); //Service에 getMessage method 실행
		model.addAttribute("message", message); //return 받은 값을 model에 저장
		
		return "other";
	}
}
