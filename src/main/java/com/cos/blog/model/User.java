package com.cos.blog.model;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!!
//ORM -> 자바언어(모든언어)의 오브젝트를 ->테이블로 매핑해 줌 
@Entity //user 클래스가 MySQL에 테이블이 생성된다
//@DynamicInsert //insert시에 null인 필드를 제외 시켜준
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //오라클 : 시퀀스 , MySql : auto_increment 로 넘버링 하는 전략 
	
	@Column(nullable = false, length =30, unique=true)
	private String username;
	
	@Column(nullable = false, length =100) //해쉬 사용해서 비밀번호 암호화 할것
	private String password;
	
	@Column(nullable = false, length =50)
	private String email;
	
	//@ColumnDefault("user")
	//DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp //시간 자동 입력.
	private Timestamp createDate;
}
