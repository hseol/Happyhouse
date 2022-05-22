package com.ssafy.vue.service;

import com.ssafy.vue.dto.MemberDto;


public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean insertMember(MemberDto memberDto);
	public boolean updateMember(MemberDto memberDto);
	public MemberDto renewInfo(String userid) throws Exception;
	public boolean deleteMember(String userid);
}
