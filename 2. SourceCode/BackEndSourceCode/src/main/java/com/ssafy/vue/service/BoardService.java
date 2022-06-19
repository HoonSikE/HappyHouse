package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Board;

public interface BoardService {
	// 게시판 목록 조회
	public List<Board> retrieveBoard(String pg);
	// 게시판 검색 조회
	public List<Board> findSearch(String pg, String search, String type);
	// 게시판 세부 조회
	public Board detailBoard(int articleno);
	// 게시판 추가
	public boolean writeBoard(Board board);
	// 게시판 수정
	public boolean updateBoard(Board board);
	// 조회수 증가
	public boolean increaseHit(int articleno);
	// 게시판 삭제
	public boolean deleteBoard(int articleno);
}
