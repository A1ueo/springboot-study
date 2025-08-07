package com.winter.app.board.qna;

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
public class QnaService implements BoardService {

	@Autowired
	QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager;
    
    @Value("${app.upload}")
    private String upload;
    
    @Value("${board.qna}")
    private String board;
	
	@Override
	public int insert(BoardVO boardVO, MultipartFile attaches) throws Exception {
		int result = qnaMapper.insert(boardVO);
		
		result = qnaMapper.updateRef(boardVO);
		
		String fileName = fileManager.fileSave(upload + board, attaches);
		
		BoardFileVO fileVO = new BoardFileVO();
		
		fileVO.setOriName(attaches.getOriginalFilename());
		fileVO.setSaveName(fileName);
		fileVO.setBoardNum(boardVO.getBoardNum());
		
		result = qnaMapper.insertFile(fileVO);
		
		return result;
	}
	
	public int reply(QnaVO qnaVO) throws Exception {
		QnaVO parent = (QnaVO) qnaMapper.detail(qnaVO);
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardStep(parent.getBoardStep() + 1);
		qnaVO.setBoardDepth(parent.getBoardDepth() + 1);
		
		int result = qnaMapper.updateSteps(parent);
		result = qnaMapper.insertReply(qnaVO);
		
		return result;
	}

	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return qnaMapper.detail(boardVO);
	}

	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = qnaMapper.totalCount(pager);
		
		pager.makeNum(totalCount);
		
		return qnaMapper.list(pager);
	}

	@Override
	public int update(BoardVO boardVO) throws Exception {
		return 0;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return 0;
	}

}
