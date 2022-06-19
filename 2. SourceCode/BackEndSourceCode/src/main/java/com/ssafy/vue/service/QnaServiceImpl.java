package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.QnaDto;
import com.ssafy.vue.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	SqlSession sqlSession;

	@Override
	public boolean writeQna(QnaDto qnaDto) throws Exception {
		// 글 쓰기
		return sqlSession.getMapper(QnaMapper.class).writeQna(qnaDto) == 1;
	}

	@Override
	public List<QnaDto> listQna() throws SQLException {
		// 글 가져오기
		return sqlSession.getMapper(QnaMapper.class).listQna();
	}

	@Override
	public List<QnaDto> listQnaByName(String subject) throws SQLException {
		return sqlSession.getMapper(QnaMapper.class).listQnaByName(subject);
	}

	@Override
	public List<QnaDto> listQnaById(String userid) throws SQLException {
		return sqlSession.getMapper(QnaMapper.class).listQnaById(userid);
	}

	@Override
	public QnaDto getQna(int qnano) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).getQna(qnano);
	}

	@Override
	public void updateHit(int qnano) throws Exception {
		sqlSession.getMapper(QnaMapper.class).updateHit(qnano);
	}

	@Override
	public boolean modifyQna(QnaDto qnaDto) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).modifyQna(qnaDto) == 1;
	}

	@Override
	public void deleteMemo(int qnano) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.getMapper(QnaMapper.class).deleteMemo(qnano);
	}

	@Override
	@Transactional
	public boolean deleteQna(int qnano) throws Exception {
		deleteMemo(qnano);
		return sqlSession.getMapper(QnaMapper.class).deleteQna(qnano) == 1;
	}

	@Override
	public void deleteQnaUserid(String userid) throws Exception {
		sqlSession.getMapper(QnaMapper.class).deleteQnaUserid(userid);
	}

}
