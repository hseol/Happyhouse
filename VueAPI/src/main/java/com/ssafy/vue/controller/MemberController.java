package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.service.JwtServiceImpl;
import com.ssafy.vue.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController V1")
@RestController
@RequestMapping("/user")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
		
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
//	@PostMapping("/memberInsert")
//	public String memberInsert(MemberDto memberDto) throws Exception {
//		System.out.println(memberDto);
//		memberService.memberInsert(memberDto);
//		return "ok";
//	}
	
    @ApiOperation(value = "회원가입", notes = "회원가입을 한다 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/memberInsert")
	public ResponseEntity<String> insertMember(@RequestBody MemberDto memberDto) {
		logger.debug("registerMember - 호출");
		System.out.println("여기 호출하나요?");
		System.out.println(memberDto);
			if (memberService.insertMember(memberDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "회원정보 수정", notes = "아이디에 해당하는 회원정보수정. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{userid}")
	public ResponseEntity<String> updateMember(@RequestBody MemberDto memberDto) {
		logger.debug("updateMember - 호출");
		
		if (memberService.updateMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
	
    @ApiOperation(value = "회원삭제", notes = "아이디에 해당하는 회원정보를 삭제 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{userid}")
	public ResponseEntity<String> deleteMember(@PathVariable String userid) {
		logger.debug("deleteMember - 호출");
		if (memberService.deleteMember(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
//	@PostMapping("memberInfo")
//	public MemberDto memberInfo(HttpServletRequest request) {
//		//System.out.println("화이팅~"+request.getParameter("id"));
//		String id =request.getParameter("id");
//		return memberService.memberInfo(id);
//	}
	
//	@PostMapping("memberUpdate")
//	public String memberUpdate(MemberDto memberDto) {
//		System.out.println(memberDto);
//		memberService.memberUpdate(memberDto);
//		return "ok";
//	}
//	
//
//	@PostMapping("logout")
//	public void logout(HttpServletRequest request) {
//		HttpSession session = request.getSession(false);
//		if(session!=null) {
//		session.invalidate();
//		}
//		
//	}
//	
//			
//	@PostMapping("selectMemberById")
//	public String selectMemberById(String id){		
//		System.out.println(id);
//		JSONObject json=new JSONObject();
//		
//		try {
//			MemberDto m=memberService.selectMemberById(id);
//			//System.out.println(m);
//			if(m!=null) {
//				json.put("msg", "존재하는 아이디입니다");
//			}else {
//				json.put("msg", "사용할 수 있는 아이디입니다");
//			}
//		}catch(Exception e) {
//			json.put("msg", e.getMessage());
//		}	
//		return json.toJSONString();
//	}

}
