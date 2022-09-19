package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.apache.catalina.startup.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerT {
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	//email,password
	
	@DeleteMapping("dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제를 실패하였습니다. 해당 id는 db에 없습니다.";
		}
		return "삭제되었습니다.id : " +id;
	}
	@Transactional
	//@Transactional 어노테이션을 사용하면, userRepository.save(user); 를 쓰지 않더라도 값을 변경하면
	//데이터가 update된다. 이것을 '더티체킹'이라 한다.
	//함수 종료시 자동 커밋이 됨
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {//json 데이터를 요청->Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 알려준다)(그 때 필요한 어노테이션이 @RequestBody)
		System.out.println("id: " +id);
		System.out.println("password: " +requestUser.getPassword());
		System.out.println("email: " +requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
			//save함수는 id를 전달하지 않으면 insert를 해주고
			//id를 전달하고 id에 데이터가 있으면 update
			//데이터 없으면 insert
		return user;
		
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();		
	}
	
	//한 페이지당 2건의 데이터를 리턴 받아 볼 예정.
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return pagingUser;
	}
	
	//{id}주소로 파라미터를 전달 받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) throws IllegalAccessException {
		//user/4를 찾으면 내가 데이터베이스에서 못찾으면 user가 null이 될것아냐?
		//그럼 return값이 null이 리턴 되는데.. 그럼 프로그램 문제가 있지 않겠나?
		//Optional로 너의 User객체를 감싸서 가져 올테니 null인지 아닌지 판단해서 return해!
		
//람다식은
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.")
//		});
		
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalAccessException>() {
			@Override
			public IllegalAccessException get() {
				// TODO Auto-generated method stub
				return new IllegalAccessException("해당 유저는 없습니다. id :"+ id);
			}
		});
		//요청 : 웹브라우저
		//근데 user 객체는 자바 오브젝트이다.
		//변환 해야한다 (웹브라우저가 이해 할수 있게) ->Json
		//스프링 부트에는 MessageConverter가 응답시 자동 실행
		//만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		//user 오브젝트를 json으로 변환하여 브라우저에게 던져줌.
		return user;
	}
	
	
	
	//http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username,password,emil 데이터를 가지고 요청.
	@PostMapping("/dummy/join")
	public String join(User user) {//key=value(약속된 규칙)
		System.out.println("id :" + user.getId());
		System.out.println("username :" + user.getUsername());
		System.out.println("password :" + user.getPassword());
		System.out.println("email :" + user.getEmail());
		System.out.println("role :" + user.getRole());
		System.out.println("createDate :" + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
	
}
