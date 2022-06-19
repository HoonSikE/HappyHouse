package com.ssafy.vue.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.MemberDto;

@Mapper
public interface MemberMapper {
	MemberDto login(MemberDto dto);
	int join(MemberDto dto);
	int updateMember(MemberDto dto);
	int deleteMember(String id);
	MemberDto findById(String id);
}
