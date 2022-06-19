package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseMapService {

	// 시도 리스트
	List<SidoGugunCodeDto> getSido() throws Exception;
	// 구군 리스트
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	// 동인군 리스트
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	// dong Apt 리스트
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	// 이름 Apt 리스트
//	List<HouseInfoDto> getAptInName(String name) throws Exception;
	// 코드 Apt 리스트
//	List<AptInfoDto> getAptInfoInCode(String aptCode) throws Exception;
}
