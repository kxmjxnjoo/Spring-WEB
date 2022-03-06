package com.ezen.springmvc;

import org.springframework.stereotype.Repository;

//만들어진 클래스를 스프링 컨테이너(스프링 프리엠워크에서 제공)에 
//넣으면 어노테이션 @Repository를 사용함
@Repository
public class HomeDao {

	public String getMessage() {
		
		return "Dao에서 안녕하세요라고 인사합니다";
	}	
}