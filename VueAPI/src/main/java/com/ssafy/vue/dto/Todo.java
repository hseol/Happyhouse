package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "Todo(할일)", description = "번호, 작성자아이디, 내용, 작성일을 가진   Domain Class")
public class Todo {
	@ApiModelProperty(value = "번호")
	int no;
	@ApiModelProperty(value = "작성자아이디")
	String userid;
	@ApiModelProperty(value = "내용")
	String content;
	@ApiModelProperty(value = "작성일")
	String regtime;
	@ApiModelProperty(value = "완료여부")
	boolean iscompleted;
	
}
