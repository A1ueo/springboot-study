package com.study.a1ueo.board;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
//@Entity		// 해당 객체가 JPA에서 관리하고 있다라는것을 정의, 필수
//@Table(name="notice")		// DB에 존재하는 테이블 이름을 매핑, 생략하면 클래스명이 테이블명이 됨
public class BoardVO {

	@Id	// PK 필수
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// GenerationType.IDENTITY: Auto Increment
	private Long boardNum;
//	@Column(name = "bn", nullable = true, unique = false, length = 255, precision = 1, scale = 1,
//			insertable = true, updatable = true)
	private String boardTitle;
	private String boardWriter;
//	@Column(columnDefinition = "LONGTEXT")
	@Lob
	private String boardContents;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private LocalDateTime boardDate;
	@Column(columnDefinition = "BIGINT DEFAULT 0", insertable = false, nullable = false)
	private Long boardHit;
	
	@Transient	// DB에 사용되지 않음
	private String kind;
}
