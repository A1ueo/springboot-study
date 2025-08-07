package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.common.FileManager;
import com.winter.app.common.Pager;


@Service
public class NoticeService implements BoardService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
    
    @Value("${app.upload}")
    private String upload;
    
    @Value("${board.notice}")
    private String board;

	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = noticeDAO.totalCount(pager);
		
		pager.makeNum(totalCount);
		
		return noticeDAO.list(pager);
	}

	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return noticeDAO.detail(boardVO);
	}
	
	@Override
	public int insert(BoardVO boardVO, MultipartFile attaches) throws Exception {
		int result = noticeDAO.insert(boardVO);
		
		if (attaches == null || attaches.isEmpty()) {
			return result;
		}
		
		// 1. File을 HDD에 저장
		String fileName = fileManager.fileSave(upload + board, attaches);
		
		// 2. 저장된 파일의 정보를 DB에 저장
		BoardFileVO fileVO = new BoardFileVO();
		
		fileVO.setOriName(attaches.getOriginalFilename());
		fileVO.setSaveName(fileName);
		fileVO.setBoardNum(boardVO.getBoardNum());
		
		result = noticeDAO.insertFile(fileVO);
		
		return result;
	}
	
	@Override
	public int update(BoardVO boardVO) throws Exception {
		return noticeDAO.update(boardVO);
	}
	
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return noticeDAO.delete(boardVO);
	}
}
