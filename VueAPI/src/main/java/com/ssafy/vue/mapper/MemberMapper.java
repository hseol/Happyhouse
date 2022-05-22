package com.ssafy.vue.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.MemberDto;


@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public boolean insertMember(MemberDto memberDto);
	public boolean updateMember(MemberDto memberDto);
	public boolean deleteMember(String userid);
	
	
}