package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}
	
//	@Override
//	public List<HouseInfoDto> getAptInName(String name) throws Exception {
//		// TODO Auto-generated method stub
//		String name2 = "%" + name + "%"; 
//		return sqlSession.getMapper(HouseMapMapper.class).getAptInName(name2);
//	}
//
//	@Override
//	public List<AptInfoDto> getAptInfoInCode(String aptCode) throws Exception {
//
//		return sqlSession.getMapper(HouseMapMapper.class).getAptInfoInCode(aptCode);
//	}

}
