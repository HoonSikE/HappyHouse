package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.QnaDto;

public interface QnaService {

	// 글쓰기
	public boolean writeQna(QnaDto qnaDto) throws Exception;

	// 리스트 들고오기
	public List<QnaDto> listQna() throws SQLException;
	
	// 리스트 들고오기
	public List<QnaDto> listQnaByName(String subject) throws SQLException;

	// 리스트 들고오기
	public List<QnaDto> listQnaById(String userid) throws SQLException;
	
	// Qna 정보 들고오기
	public QnaDto getQna(int qnano) throws Exception;

	// 조회수 증가 시키기
	public void updateHit(int articleno) throws Exception;

	// 조회수 변경하기
	public boolean modifyQna(QnaDto qnaDto) throws Exception;

	// 댓글 지우기
	public void deleteMemo(int qnano) throws Exception;

	// Qna글 지우기
	public boolean deleteQna(int qnano) throws Exception;

	// user Qna 글 지우기
	public void deleteQnaUserid(String userid) throws Exception;
}
