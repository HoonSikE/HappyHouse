package com.ssafy.vue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper memberMapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	@Override
	public int join(MemberDto dto) {
		return memberMapper.join(dto);
	}

	@Override
	public MemberDto login(MemberDto dto) {
		return  memberMapper.login(dto);
	}

	@Override
	@Transactional
	public int updateMember(MemberDto dto) {
		return memberMapper.updateMember(dto);
	}

	@Override
	@Transactional
	public int deleteMember(String id) {
		return memberMapper.deleteMember(id);
	}

	@Override
	public MemberDto findById(String id) {
		return memberMapper.findById(id);
	}
}
