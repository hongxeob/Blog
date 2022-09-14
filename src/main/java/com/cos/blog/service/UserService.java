package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 .IoC해준다.
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional // 이 밑의 코드들이 하나의 트랜잭션이 되어서 성공을 하면 하나의 커밋이 됨.
	public void 회원가입(User user) {
		userRepository.save(user);

	}
}
