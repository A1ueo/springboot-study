package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;

@Service
public class QnaService implements BoardService {

	@Autowired
	QnaMapper qnaMapper;
	
	@Override
	@Transactional
	public int insert(BoardVO boardVO) throws Exception {
		int result = qnaMapper.insert(boardVO);
		
		if (result != 1) throw new RuntimeException("insert 트랜잭션 오류");
		
		result = qnaMapper.updateRef(boardVO);
		
		if (result != 1) throw new RuntimeException("insert 트랜잭션 오류");
		
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
	public List<BoardVO> list() throws Exception {
		return qnaMapper.list();
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
