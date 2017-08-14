package com.hanbit.there.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.there.api.domain.Airline;
import com.hanbit.there.api.service.AirlineService;

@RestController
public class AirlineController {

	@Autowired
	private AirlineService airlineService;

	@RequestMapping("/airline/{id}")
	public Airline getAirline(@PathVariable("id") String id) {
		return airlineService.getAirline(id);
	}

}
