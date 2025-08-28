package com.study.a1ueo.board.notice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.a1ueo.board.BoardVO;

public interface NoticeRepository extends JpaRepository<BoardVO, Long> {

}
