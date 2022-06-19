package com.ssafy.vue.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.QnaDto;
import com.ssafy.vue.service.QnaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/qna")
@Api("Qna 컨트롤러  API V1")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaService qnaService;

	@ApiOperation(value = "Qna 글작성", notes = "새로운  질문글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQan(@RequestBody @ApiParam(value = "질문글 정보.", required = true) QnaDto qnaDto)
			throws Exception {
		logger.info("writeQna - 호출");
		if (qnaService.writeQna(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "질문글 목록", notes = "모든 질문글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnaDto>> listQna() throws Exception {
		logger.info("listQna - 호출");
		return new ResponseEntity<List<QnaDto>>(qnaService.listQna(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "질문글 목록", notes = "모든 질문글의 정보를 반환한다.", response = List.class)
	@GetMapping("/subject")
	public ResponseEntity<List<QnaDto>> listQnaByName(@RequestParam("subject") String subject) throws Exception {
		logger.info("listQnaByName - 호출");
		return new ResponseEntity<List<QnaDto>>(qnaService.listQnaByName(subject), HttpStatus.OK);
	}
	
	@ApiOperation(value = "질문글 목록", notes = "이름을 받아 모든 질문글의 정보를 반환한다.", response = List.class)
	@GetMapping("/userid")
	public ResponseEntity<List<QnaDto>> listQnaById(@RequestParam("userid") String userid) throws Exception {
		logger.info("listQnaById - 호출");
		return new ResponseEntity<List<QnaDto>>(qnaService.listQnaById(userid), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = QnaDto.class)
	@GetMapping("/{qnano}")
	public ResponseEntity<QnaDto> getQna(
			@PathVariable("qnano") @ApiParam(value = "얻어올 질문글 번호.", required = true) int qnano) throws Exception {
		logger.info("getQna - 호출 : " + qnano);
		qnaService.updateHit(qnano);
		return new ResponseEntity<QnaDto>(qnaService.getQna(qnano), HttpStatus.OK);
	}

	@ApiOperation(value = "질문글 글수정", notes = "질문글을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyQna(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) QnaDto qnaDto)
			throws Exception {
		logger.info("modifyQna - 호출");

		if (qnaService.modifyQna(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@ApiOperation(value = "질문글 글삭제", notes = "글번호에 해당하는 질문글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{qnano}")
	public ResponseEntity<String> deleteQna(
			@PathVariable("qnano") @ApiParam(value = "살제할 글의 글번호.", required = true) int qnano) throws Exception {
		logger.info("deleteQna - 호출");
		if (qnaService.deleteQna(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}