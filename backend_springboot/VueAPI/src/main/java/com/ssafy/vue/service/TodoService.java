package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Todo;

public interface TodoService {
	public List<Todo> retrieveTodo(String userid);

	public boolean insertTodo(Todo todo);

	public boolean deleteTodo(int no);
}
