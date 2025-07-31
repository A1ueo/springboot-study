package com.winter.app.board;

import java.util.List;

import com.winter.app.board.notice.NoticeVO;

public interface BoardDAO {

	// insert
	int insert(BoardVO boardVO) throws Exception;
	int update(BoardVO boardVO) throws Exception;
	int delete(long boardNum) throws Exception;
	BoardVO detail(BoardVO boardVO) throws Exception;
	List<BoardVO> list() throws Exception;
	NoticeVO select(BoardVO param);
}
