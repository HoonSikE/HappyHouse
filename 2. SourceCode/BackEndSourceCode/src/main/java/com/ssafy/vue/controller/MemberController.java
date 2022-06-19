package com.ssafy.vue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.service.MemberService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
    @ApiOperation(value = "회원 정보를 반환한다.", response = List.class)
	@GetMapping("/item")
	public ResponseEntity<MemberDto> getMember(@RequestParam("id") String id, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("loginId");
		
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<MemberDto>(memberService.findById(id), HttpStatus.OK);
	}
	
    @ApiOperation(value = "회원가입을 한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/join")
	public ResponseEntity<String> joinMember(@RequestBody MemberDto member) {
		logger.debug("joinMember - 호출");
		logger.debug("" + member);
		
		if (memberService.join(member) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "로그인을 한다. 그리고 성공여부에 따라 'loginId' 또는 'null' 문자열을 반환한다.", response = String.class)
	@PostMapping("/login")
	public ResponseEntity<MemberDto> login(@RequestParam("id") String id, @RequestParam("pw") String password, HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	session.setAttribute("loginId", id);
    	
    	logger.debug("login - 호출");
    	MemberDto member = new MemberDto(id, password);
		logger.debug("" + member);

		return new ResponseEntity<MemberDto>(memberService.login(member), HttpStatus.OK);
	}
    
    @ApiOperation(value = "로그아웃한다. 그리고 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@GetMapping("/logout")
	public ResponseEntity<String> toLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
    
    @ApiOperation(value = "회원 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PutMapping("/update")
	public ResponseEntity<String> UpdateMember(HttpServletRequest request, @RequestBody MemberDto memberDto) {
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("loginId");
		
		logger.debug("UpdateMember - 호출");
		logger.debug("" + memberDto);
		System.out.println(memberDto);
		
//		if(!memberDto.getId().equals(id))
//			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
		if (memberService.updateMember(memberDto) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
    @ApiOperation(value = "회원 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(@RequestParam("id") String id, HttpServletRequest request) {
//    	HttpSession session = request.getSession();
//		String sessionId = (String)session.getAttribute("loginId");

//		if(!sessionId.equals(id))
//			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
		logger.debug("deleteMember - 호출");
		if (memberService.deleteMember(id) == 1) {
//			session.invalidate();
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	/*
	GET /member/join - 회원가입 페이지로 이동
	GET /member/login - 로그인 페이지로 이동
	GET /member/item - 회원정보 페이지로 이동 v
	GET /member/logout - 로그아웃 v
	GET /member/update - 회원정보수정 페이지로 이동 v
	POST /member/join - 회원가입
	POST /member/login - 로그인
	POST /member/delete - 회원탈퇴 v
	POST /member/update - 회원수정
	*/
	
	
}
