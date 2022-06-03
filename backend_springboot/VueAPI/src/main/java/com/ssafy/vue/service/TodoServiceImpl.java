package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.Todo;
import com.ssafy.vue.mapper.TodoMapper;
@Service
public class TodoServiceImpl implements TodoService {
	@Autowired
	private TodoMapper todoMapper;
	
	
	@Override
	public List<Todo> retrieveTodo(String userid) {
		return todoMapper.selectTodo(userid);
	}

	@Override
	public boolean insertTodo(Todo todo) {
		return todoMapper.insertTodo(todo)==1;
	}

	@Override
	public boolean deleteTodo(int no) {
		return todoMapper.deleteTodo(no)==1;
	}

}
