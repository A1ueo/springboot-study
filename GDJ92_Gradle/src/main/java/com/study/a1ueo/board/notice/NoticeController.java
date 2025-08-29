package com.study.a1ueo.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	// 페이징처리
	// https://juntcom.tistory.com/219
	
	
	@GetMapping("")
	public Page<NoticeVO> list(@PageableDefault(
			sort = "boardNum", direction = Sort.Direction.DESC
			) Pageable pageable) throws Exception {
		return noticeService.list(pageable);
	}
	
	@GetMapping("{boardNum}")
	public NoticeVO detail(@PathVariable("boardNum") Long boardNum) throws Exception {
		NoticeVO noticeVO = noticeService.detail(boardNum);
		return noticeVO;
	}
}
