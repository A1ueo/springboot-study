package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.common.Pager;

public interface BoardService {

	int insert(BoardVO boardVO, MultipartFile[] attaches) throws Exception;
	BoardVO detail(BoardVO boardVO) throws Exception;
	List<BoardVO> list(Pager pager) throws Exception;
	int update(BoardVO boardVO, MultipartFile[] attaches) throws Exception;
	int delete(BoardVO boardVO) throws Exception;
	int deleteOneFile(BoardFileVO boardFileVO) throws Exception;
	BoardFileVO fileDetail(BoardFileVO boardFileVO) throws Exception;
}
