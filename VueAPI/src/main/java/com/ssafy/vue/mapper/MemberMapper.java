package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.MemberDto;



@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public boolean insertMember(MemberDto memberDto);
	public boolean updateMember(MemberDto memberDto);
	public boolean updatePic(MemberDto memberDto);
	public boolean deleteMember(String userid);
	public int checkId(String userid);
}