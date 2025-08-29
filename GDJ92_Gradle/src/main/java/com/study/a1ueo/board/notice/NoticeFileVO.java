package com.study.a1ueo.board.notice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "notice_file")
public class NoticeFileVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileNum;
//	private Long boardNum;
	private String saveName;
	private String oriName;
	

	@JoinColumn(name = "board_num")
	@ManyToOne
	@JsonIgnore // JSON 직렬화 할 때 제외
	private NoticeVO noticeVO;
}
