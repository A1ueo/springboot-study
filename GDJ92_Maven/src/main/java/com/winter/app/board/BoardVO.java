package com.winter.app.board;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {

	private Long boardNum;
	// NotNull, NotEmpty, NotBlank
	@NotBlank
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private LocalDate boardDate;
	private Long boardHit;

	private List<BoardFileVO> boardFileVOs;
}
