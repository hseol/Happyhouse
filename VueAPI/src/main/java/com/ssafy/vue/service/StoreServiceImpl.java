package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.Store;
import com.ssafy.vue.mapper.StoreMapper;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreMapper storeMapper;

	@Override
	public List<Store> retrieveStore() {
		List<Store> list = storeMapper.selectStore();
		for(Store store:list) {
			String stname = store.getStname();
			stname.replaceAll("<","&lt;").replaceAll(">","&gt;");
		}
		return list;
	}
	
//	public List<StoreVO> selectAllStore(){
//		List<StoreVO> list = storeDAO.selectAllStore();
//		//방어코드
//		for(StoreVO vo:list) {
//			String stname = vo.getStname();
//			stname.replaceAll("<","&lt;").replaceAll(">","&gt;");
//		}
//		return list;
//	}
}
