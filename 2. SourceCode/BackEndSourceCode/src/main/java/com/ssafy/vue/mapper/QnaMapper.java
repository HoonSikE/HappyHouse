package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.QnaDto;

@Mapper
public interface QnaMapper {
	// 글쓰기
	public int writeQna(QnaDto qnaDto) throws SQLException;

	// 리스트 들고오기
	public List<QnaDto> listQna() throws SQLException;

	// 리스트 들고오기
	public List<QnaDto> listQnaByName(String subject) throws SQLException;

	// 리스트 들고오기
	public List<QnaDto> listQnaById(String userid) throws SQLException;

	// Qna 정보 들고오기
	public QnaDto getQna(int qnano) throws SQLException;

	// 조회수 증가 시키기
	public void updateHit(int qnano) throws SQLException;

	// 조회수 변경하기
	public int modifyQna(QnaDto qnaDto) throws SQLException;

	// 댓글 지우기
	public void deleteMemo(int qnano) throws SQLException;

	// Qna글 지우기
	public int deleteQna(int qnano) throws SQLException;

	// user Qna 글 지우기
	public void deleteQnaUserid(String userid) throws SQLException;

}