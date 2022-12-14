package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
	private int id;
	
	@Column(nullable =false,length=100)
	private String title;
	
	@Lob//대용량 데이터 쓸 때 사용 
	private String content; //섬머노트 라이브러리 사용 할것이다 <html>태그가 섞여서 디자인 되기 때문에 데이터가 커
	
	private int count; //조회수 
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board , User = One ->한명의 유저는 많은 게시물을 쓸 수 있다.(앞에께 현재 오브젝트 뒤에께 새로 만든 아이)
	private User user; //DB는 오브젝트를 저장할 수 없다. ->FK 사용. *자바는 오브젝트를 저장할 수 있다.
	
	//하나의 게시물은 많은 리플을 가질 수 있다.(앞에께 현재 오브젝트 뒤에께 새로 만든 아이)
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아님(난FK아님) 나의 주인(FK)은 리플 테이블의 보드 이다.
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp //시간 자동 입력 됨.
	private Timestamp createDate;
}
