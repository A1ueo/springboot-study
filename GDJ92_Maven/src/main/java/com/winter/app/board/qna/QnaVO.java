package com.winter.app.board.qna;

import com.winter.app.board.BoardVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QnaVO extends BoardVO {

	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
}
