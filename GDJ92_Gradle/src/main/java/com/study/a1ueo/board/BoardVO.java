package com.study.a1ueo.board;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity		// 해당 객체가 JPA에서 관리하고 있다라는것을 정의, 필수
@Table(name="notice")		// DB에 존재하는 테이블 이름을 매핑, 생략하면 클래스명이 테이블명이 됨
public class BoardVO {

	@Id		// PK 필수
	// GenerationType.IDENTITY: Auto Increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	private LocalDateTime boardDate;
	private Long boardHit;
}
