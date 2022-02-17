package com.junmo.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 - > 응답 (HTML 파일)
//@Controller

// 사용자가 요청 - > 응답 ( Data )
@RestController
public class HttpControllerTest {
	
	private static final String TAG ="HttpControllerTest:";
	
	//localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m=Member.builder().username("jm").password("1234").email("yymeyy3@naver.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("jjm");
		System.out.println(TAG+"setter : "+m.getUsername());
		return "lombok test 완료";
	
	}
	
	
	//인터넷 브라우저 요청은 무조건 get 요청만 사용가능
	//http://localhost:8080/http/get (select) 선택
	@GetMapping("/http/get")
	public String getTest(Member m) { //?id=1&username=junmo&password=1234&email=yymeyy3@naver.com 스프링의 컨트롤러가 멤버의 변수에 자동으로 넣어줌. MessageConverter
				return "get 요청"+m.getId()+m.getUsername()+m.getPassword()+m.getEmail();
	}
	//http://localhost:8080/http/post (insert) 추가 삽입
	@PostMapping("/http/post") //Body 입력값을 JSON으로받음
	public String postTest(@RequestBody Member m) { //MessageConverter(스프링부트)
		return "post요청"+m.getId()+m.getUsername()+m.getPassword()+m.getEmail();
	}
	//http://localhost:8080/http/put (update) 수정
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+m.getId()+m.getUsername()+m.getPassword()+m.getEmail();
	}
	//http://localhost:8080/http/delete (delete) 삭제
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete  요청";
	}
	
}
