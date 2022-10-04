package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;//시퀀스,auto_increment
	
	@Column(nullable = false, length=200)
	private String content;
	
	@ManyToOne//여러개의 답변은 하나의 답변에 존재 할 수있다.(앞에께 현재 오브젝트 뒤에께 새로 만든 아이)
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne//여러개의 답변을 하나의 유저가 쓸 수 있다.(앞에께 현재 오브젝트 뒤에께 새로 만든 아이)
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;

	@Override
	public String toString() {
		return "Reply [id=" + id + ", content=" + content + ", board=" + board + ", user=" + user + ", createDate="
				+ createDate + "]";
	}
	
	
}
