package com.ssafy.vue.service;

import com.ssafy.vue.dto.MemberDto;


public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean insertMember(MemberDto memberDto);
	public boolean updateMember(MemberDto memberDto);
	public boolean updatePic(MemberDto memberDto);
	public boolean deleteMember(String userid);
	public int checkId(String userid);
}
