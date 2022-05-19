package com.ssafy.vue.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Store {
private String stname,sttype,dongname,fulljibun;
private int dongcode;
private float gyeongdo,wedo;
}
