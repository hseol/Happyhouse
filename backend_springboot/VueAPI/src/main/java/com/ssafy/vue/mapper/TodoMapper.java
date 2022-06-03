package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Todo;
@Mapper
public interface TodoMapper {
	public List<Todo>selectTodo(String userid);
	public int insertTodo(Todo todo);
	public int deleteTodo(int no);
}
