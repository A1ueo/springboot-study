package com.study.a1ueo.board.notice;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {

	List<NoticeVO> findByBoardTitleContaining(String keyword, Pageable pageable) throws Exception;
}
