package com.ssafy.vue.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.mapper.BoardMapper;
import com.ssafy.vue.util.Paging;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
	private BoardMapper boardMapper;

    @Override
	public List<Board> retrieveBoard(String pg) {
    	int page = pg == null ? 1 : Integer.parseInt(pg);

		Paging paging = new Paging(boardMapper.getTotalCount(), page, 10, 10);
		return boardMapper.selectBoard(paging);
	}
    
	@Override 
	public List<Board> findSearch(String pg, String search, String type){
		int page = pg == null ? 1 : Integer.parseInt(pg);

		Paging paging = new Paging(boardMapper.getSearchCount(search), page, 10, 10);
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("search", search);
		params.put("paging", paging);
		params.put("type", type);
		
		return boardMapper.findSearch(params);
	}
    
  	@Override
	public boolean writeBoard(Board board) {
		return boardMapper.insertBoard(board) == 1;
	}

	@Override
	public Board detailBoard(int articleno) {
		return boardMapper.selectBoardByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		return boardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean increaseHit(int articleno) {
		return boardMapper.increaseHit(articleno) == 1;
	}
		
	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}
}