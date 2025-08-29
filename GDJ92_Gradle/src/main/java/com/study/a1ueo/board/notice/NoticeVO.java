package com.study.a1ueo.board.notice;

import java.util.List;

import com.study.a1ueo.board.BoardVO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity	// 해당 객체가 JPA에서 관리하고 있다라는것을 정의, 필수
@Table(name="notice")	// DB에 존재하는 테이블 이름을 매핑, 생략하면 클래스명이 테이블명이 됨
public class NoticeVO extends BoardVO {

	@OneToMany(mappedBy = "noticeVO", fetch = FetchType.EAGER)
	private List<NoticeFileVO> list;
}
