package com.winter.app.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.winter.app.board.BoardFileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileDownView extends AbstractView {

	@Value("${app.upload}")
	private String path;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BoardFileVO boardFileVO = (BoardFileVO) model.get("vo");
		
		String filePath = path + model.get("title").toString();
		
		File file = new File(filePath, boardFileVO.getSaveName());
		
		// 총 파일의 크기
		response.setContentLengthLong(file.length());
		
		// 파일 다운시 파일의 이름을 인코딩
		String fileName = URLEncoder.encode(boardFileVO.getOriName(), "UTF-8");
		
		// header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// File을 읽어서
		FileInputStream fis = new FileInputStream(file);
		// Client로 연결
		OutputStream os = response.getOutputStream();
		// 전송
		FileCopyUtils.copy(fis, os);
		
		// 자원 해제
		os.close();
		fis.close();
	}
}
