package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.service.HouseMapService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	
	@ApiOperation(value = "시도 정보", notes = "전국 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "구군 정보", notes = "전국 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value ="동 정보", notes = "전국 동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value ="아파트 정보", notes = "동의 아파트 리스트를 반환한다.", response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
//	@ApiOperation(value ="아파트 정보", notes = "해당하는 구군 아파트 리스트르 반환한다.", response = List.class)
//	@GetMapping("/search")
//	public ResponseEntity<List<HouseInfoDto>> search(@RequestParam("aptName") String aptName) throws Exception {
//		logger.info("search - 아파트 정보 검색 호출");
//		System.out.println(aptName);
//		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInName(aptName), HttpStatus.OK);
//	}
//	
//	@ApiOperation(value ="아파트 정보", notes = "해당하는 아파트 코드의 아파트 정보 리스트르 반환한다.", response = List.class)
//	@GetMapping("/deal")
//	public ResponseEntity<List<AptInfoDto>> aptInfo(@RequestParam("aptCode") String aptCode) throws Exception{
//		return new ResponseEntity<List<AptInfoDto>>(haHouseMapService.getAptInfoInCode(aptCode),HttpStatus.OK);
//	}
}
