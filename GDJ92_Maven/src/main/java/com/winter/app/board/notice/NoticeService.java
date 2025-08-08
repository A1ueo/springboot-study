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
	public int insert(BoardVO boardVO, MultipartFile[] attaches) throws Exception {
		int result = noticeDAO.insert(boardVO);
		
		if (attaches == null) {
			return result;
		}
			
		for (MultipartFile a : attaches) {
			if (a == null || a.isEmpty()) {
				continue;
			}
			
			// 1. File을 HDD에 저장
			String fileName = fileManager.fileSave(upload + board, a);
			
			// 2. 저장된 파일의 정보를 DB에 저장
			BoardFileVO fileVO = new BoardFileVO();
			
			fileVO.setOriName(a.getOriginalFilename());
			fileVO.setSaveName(fileName);
			fileVO.setBoardNum(boardVO.getBoardNum());
			
			result = noticeDAO.insertFile(fileVO);
		}
		
		return result;
	}
	
	@Override
	public int update(BoardVO boardVO, MultipartFile[] attaches) throws Exception {
		int result = noticeDAO.update(boardVO);
		
		if (attaches == null) {
			return result;
		}
			
		for (MultipartFile a : attaches) {
			if (a == null || a.isEmpty()) {
				continue;
			}
			
			// 1. File을 HDD에 저장
			String fileName = fileManager.fileSave(upload + board, a);
			
			// 2. 저장된 파일의 정보를 DB에 저장
			BoardFileVO fileVO = new BoardFileVO();
			
			fileVO.setOriName(a.getOriginalFilename());
			fileVO.setSaveName(fileName);
			fileVO.setBoardNum(boardVO.getBoardNum());
			
			result = noticeDAO.insertFile(fileVO);
		}
		
		return result;
	}
	
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		boardVO = noticeDAO.detail(boardVO);
		
		boolean flag = false;
		
		for (BoardFileVO bf : boardVO.getBoardFileVOs()) {
			flag = fileManager.fileDelete(upload + board, bf.getSaveName());
		}
		
		int result = -1;
		result = noticeDAO.deleteFile(boardVO);
		
		result = noticeDAO.delete(boardVO);
		
		return result;
	}
	
	@Override
	public int deleteFile(BoardFileVO boardFileVO) throws Exception {
		// 1. File 조회
		boardFileVO = noticeDAO.fileDetail(boardFileVO);
		
		// 2. File 삭제
		fileManager.fileDelete(upload + board, boardFileVO.getSaveName());
		
		// 3. DB 삭제
		int result = noticeDAO.deleteOneFile(boardFileVO);
		
		return result;
	}
}
