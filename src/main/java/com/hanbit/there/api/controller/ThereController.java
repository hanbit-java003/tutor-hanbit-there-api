package com.hanbit.there.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.there.api.vo.InfoVO;
import com.hanbit.there.api.vo.LocationVO;
import com.hanbit.there.api.vo.ThereVO;
import com.hanbit.there.api.vo.TrafficVO;

@RestController
@RequestMapping("/api/there")
public class ThereController {

	@RequestMapping("/{theresId}")
	public ThereVO getThere(@PathVariable("theresId") String theresId) {
		
		ThereVO there = new ThereVO();
		there.setName("괌");
		there.setNameEn("Guam");
		there.setBackground("./img/theres/Guam.jpg");
		there.setSummary("남태평양 푸른 바다의 넘실거림을 보고있자니<br>어제가 오늘인 듯, 오늘이 어제인 듯 시간의 흐름이 느껴지지 않는곳, 괌");
		there.setTimezone("Pacific/Guam");
		
		LocationVO location = new LocationVO();
		location.setLat(13.444304);
		location.setLng(144.793732);
		there.setLocation(location);
		
		List<InfoVO> areaInfo = new ArrayList<>();
		
		InfoVO info1 = new InfoVO();
		info1.setTitle("언어");
		info1.setValue("영어");
		areaInfo.add(info1);
		
		InfoVO info2 = new InfoVO();
		info2.setTitle("화폐");
		info2.setValue("달러(USD, Doller)");
		areaInfo.add(info2);
		
		InfoVO info3 = new InfoVO();
		info3.setTitle("비자");
		info3.setValue("45일 미만 무비자 체류");
		areaInfo.add(info3);
		
		InfoVO info4 = new InfoVO();
		info4.setTitle("TIP");
		info4.setValue("110v이므로 변환플러그(돼지코)가 필요");
		areaInfo.add(info4);
		
		there.setAreaInfo(areaInfo);
		
		List<TrafficVO> traffics = new ArrayList<>();
		
		TrafficVO traffic1 = new TrafficVO();
		traffic1.setTitle("레드 괌 셔틀 버스");
		traffic1.setIcon("bus");
		traffic1.setContents("주요 호텔과 쇼핑센터는 물론 유명 레스토랑까지 노선에 포함돼 있어 유용하다.");
		traffics.add(traffic1);
		
		TrafficVO traffic2 = new TrafficVO();
		traffic2.setTitle("택시 Taxi");
		traffic2.setIcon("taxi");
		traffic2.setContents("호텔이나 쇼핑센터 같은 지정된 지점에서 탑승. 팁은 요금의 10%면 적당");
		traffics.add(traffic2);
		
		TrafficVO traffic3 = new TrafficVO();
		traffic3.setTitle("투몬 셔틀 버스");
		traffic3.setIcon("bus");
		traffic3.setContents("투몬과 타무닝 지역을 연결하며 북부, 남부노선이 있고 약 8분 간격 운행");
		traffics.add(traffic3);
		
		TrafficVO traffic4 = new TrafficVO();
		traffic4.setTitle("렌트카 Rent-a-Car");
		traffic4.setIcon("car");
		traffic4.setContents("괌 자유여행자들에게 최고의 교통수단이다. 그러다보니, 여행 전 예약해서 준비해둘것.");
		traffics.add(traffic4);
		
		TrafficVO traffic5 = new TrafficVO();
		traffic5.setTitle("갤러리아 무료 셔틀 버스");
		traffic5.setIcon("bus");
		traffic5.setContents("투몬 지역과 타무닝 지역의 주요 호텔을 3개의 노선이 운행");
		traffics.add(traffic5);
		
		there.setTraffics(traffics);
		
		return there;
	}
	
}






