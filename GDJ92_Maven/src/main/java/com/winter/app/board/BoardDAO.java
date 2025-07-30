package com.winter.app.board;

public interface BoardDAO {

	// insert
	int insert(BoardVO boardVO) throws Exception;
	int update(BoardVO boardVO) throws Exception;
	int delete(long boardNum) throws Exception;
}
