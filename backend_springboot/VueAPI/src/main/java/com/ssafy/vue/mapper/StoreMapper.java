package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Store;

@Mapper
public interface StoreMapper {
 public List<Store> selectStore();
}
