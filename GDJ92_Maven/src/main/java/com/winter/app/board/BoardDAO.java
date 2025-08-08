package com.winter.app.board;

import java.util.List;

import com.winter.app.common.Pager;

public interface BoardDAO {

	// insert
	int insert(BoardVO boardVO) throws Exception;
	// update
	int update(BoardVO boardVO) throws Exception;
	// delete
	int delete(BoardVO boardVO) throws Exception;
	// select
	BoardVO detail(BoardVO boardVO) throws Exception;
	// selectList
	List<BoardVO> list(Pager pager) throws Exception;
	// pager
	Long totalCount(Pager pager) throws Exception;
	// attach
	int insertFile(BoardFileVO boardFileVO) throws Exception;
	// 
	int deleteFiles(BoardVO boardVO) throws Exception;
	//
	BoardFileVO fileDetail(BoardFileVO boardFileVO) throws Exception;
	int deleteOneFile(BoardFileVO boardFileVO) throws Exception;
}
