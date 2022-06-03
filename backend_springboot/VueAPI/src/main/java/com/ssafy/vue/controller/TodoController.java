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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Todo;
import com.ssafy.vue.service.TodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("TodoController V1")
@RestController
@RequestMapping("/todo")
public class TodoController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private TodoService todoService;

    @ApiOperation(value = "할일 목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("{userid}")
	public ResponseEntity<List<Todo>> retrieveTodo(@PathVariable String userid) throws Exception {
		logger.debug("retrieveTodo - 호출");
		return new ResponseEntity<List<Todo>>(todoService.retrieveTodo(userid), HttpStatus.OK);
	}

    @ApiOperation(value = "할일등록", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertTodo(@RequestBody Todo todo) {
		logger.debug("insertTodo - 호출");
		if (todoService.insertTodo(todo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "할일삭제", notes = "번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteTodo(@PathVariable int no) {
		logger.debug("deleteTodo - 호출");
		if (todoService.deleteTodo(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	/*
	 * @ApiOperation(value = "게시판 글정보 수정", notes =
	 * "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.",
	 * response = String.class)
	 * 
	 * @PutMapping("{articleno}") public ResponseEntity<String>
	 * updateBoard(@RequestBody Board board) { logger.debug("updateBoard - 호출");
	 * 
	 * if (boardService.updateBoard(board)) { return new
	 * ResponseEntity<String>(SUCCESS, HttpStatus.OK); } return new
	 * ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT); }
	 */
    

}
