package com.ssafy.vue.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HouseInfoDto {
	private long aptCode;
	private String apartmentName;
	private String dongCode;
	private String dong;
	private String sidoName;
	private String gugunName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String recentPrice;


}