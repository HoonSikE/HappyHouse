package com.ssafy.vue.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.util.Paging;
@Mapper
public interface BoardMapper {
	// 공지사항 목록 조회
	public List<Board> selectBoard(Paging paging);
	// 공지사항 검색 조회
	List<Board> findSearch(HashMap<String, Object> map);
//	List<Board> findSearch(String search);
	// 공지사항 세부 조회
	public Board selectBoardByNo(int articleno);
	// 공지사항 추가
	public int insertBoard(Board board);
	// 공지사항 수정
	public int updateBoard(Board board);
	// 조회수 증가
	public int increaseHit(int articleno);
	// 공지사항 삭제
	public int deleteBoard(int articleno);
	
	int getTotalCount();
	
	int getSearchCount(String title);
}