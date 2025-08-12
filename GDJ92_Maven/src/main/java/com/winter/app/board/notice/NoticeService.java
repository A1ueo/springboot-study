package com.winter.app.board.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.common.FileManager;
import com.winter.app.common.Pager;


@Service
@Transactional
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
		
		if (totalCount == 0) {
			return new ArrayList<BoardVO>();
		}
		
		pager.makeNum(totalCount);
		
		return noticeDAO.list(pager);
	}

	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return noticeDAO.detail(boardVO);
	}
	
	@Transactional
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
		result = noticeDAO.deleteFiles(boardVO);
		
		result = noticeDAO.delete(boardVO);
		
		return result;
	}
	
	@Override
	public int deleteOneFile(BoardFileVO boardFileVO) throws Exception {
		// 1. File 조회
		boardFileVO = noticeDAO.fileDetail(boardFileVO);
		
		// 2. File 삭제
		fileManager.fileDelete(upload + board, boardFileVO.getSaveName());
		
		// 3. DB 삭제
		int result = noticeDAO.deleteOneFile(boardFileVO);
		
		return result;
	}
	
	@Override
	public BoardFileVO fileDetail(BoardFileVO boardFileVO) throws Exception {
		return noticeDAO.fileDetail(boardFileVO);
	}
	
	@Override
	public String boardFile(MultipartFile multipartFile) throws Exception {
		if (multipartFile == null || multipartFile.getSize() == 0)
			return null;
		
		String fileName = fileManager.fileSave(upload + board, multipartFile);
		
		return "/file/" + board + "/" + fileName;
	}

	@Override
	public boolean boardFileDelete(String fileName) throws Exception {
		int idx = fileName.lastIndexOf("/");
		fileName = fileName.substring(idx);
		return fileManager.fileDelete(upload + board, fileName);
	}
}
