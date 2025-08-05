package com.winter.app.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardVO;

@Mapper
public interface QnaMapper extends BoardDAO {

	int updateRef(BoardVO boardVO) throws Exception;
}
