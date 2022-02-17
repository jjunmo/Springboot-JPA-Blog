package com.junmo.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junmo.blog.model.RoleType;
import com.junmo.blog.model.User;
import com.junmo.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입 (DI)
	private UserRepository userRepository;
	
	//http://localhost:8000/blog/dummy/join
	@PostMapping("/dummy/join")
	public String join(User user) {//key=value 형태로 값을받음
		System.out.println("id :"+user.getId());
		System.out.println("role :"+user.getRole());
		System.out.println("createdate :"+user.getCreateDate());
		System.out.println("username :" + user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
