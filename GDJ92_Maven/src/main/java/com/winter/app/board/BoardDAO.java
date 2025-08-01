package com.winter.app.board;

import java.util.List;

public interface BoardDAO {

	// insert
	int insert(BoardVO boardVO) throws Exception;
	// update
	int update(BoardVO boardVO) throws Exception;
	// delete
	int delete(long boardNum) throws Exception;
	// select
	BoardVO detail(BoardVO boardVO) throws Exception;
	// selectList
	List<BoardVO> list() throws Exception;
}
