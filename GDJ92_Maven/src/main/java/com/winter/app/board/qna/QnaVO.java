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
	
	// setter
	// public void set변수명(타입 변수명) { }
	// public 타입 get변수명() { }
	public Long getBoardRef() {
		if (boardRef == null)
			this.boardRef = 0L;
		
		return this.boardRef;
	}
	
	public Long getBoardStep() {
		if (boardStep == null)
			this.boardStep = 0L;
		
		return this.boardStep;
	}
	
	public Long Depth() {
		if (boardDepth == null)
			this.boardDepth = 0L;
		
		return this.boardDepth;
	}
}
