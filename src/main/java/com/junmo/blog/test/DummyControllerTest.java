package com.junmo.blog.test;

import com.junmo.blog.model.RoleType;
import com.junmo.blog.model.User;
import com.junmo.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

//html이 아닌 data를 리턴해주는 controller=RestController
@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입 (DI)
	private UserRepository userRepository;

	//save 함수는 id를 전달하지 않거나 해당 id에 대한 데이터가 없으면 insert를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 함

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id){
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e){

			return "삭제에 실패하였습니다."+id;
		}
		return "삭제되었습니다.id"+id;
	}



	// http://localhost:8000/blog/dummy/user/{id}
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		//json 데이터를 요청 -> Java Object로 MessageConverter의 Jackson 라이브러리가 변환해서 받아줌
		System.out.println("id : "+id);
		System.out.println("password : "+ requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		requestUser.setId(id);
		
		// userRepository.save(requestUser);
		
		//더티 체킹
		//2월 18일
		return user;
	}
	
	// http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한페이장 2건의 데이터를 리턴
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id", direction=Sort.Direction.DESC ) Pageable pageable){
		//페이징
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		
		return users;
	}
	
	//{id} 주소로 파라미터를 전달 받을수 있다.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4 를 찾으면 내가 데이터베이스에서 못찾을 경우 user가 null이 됨
		//그럼 return 할때 null이 리턴 되니 문제있음
		//Optinal로 User 객체를 감싸서 가져옴 null인지 아닌지 판단해서 return 해라
		
		//람다식
		// ()로 익명객체
		//User user =userRepository.findById(id).orElseThrow(()->{return new IllegalArgumentException("해당 사용자는 없습니다.");});
		User user =userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		//변환 (웹브라우저가 이해할수 있는 데이터) - > json ( Gson 라이브러리)
		// 스프링부트 = MessageConverter  라는 애가 응답시 자동 작동
		// 만약 자바 오브젝트가 리턴시 MessageConverter가 Jackson이란 라이브러리를 호출하여 user 오브젝트를 json으로 변환해 브라우저 던짐
		return user;
	}
	
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
