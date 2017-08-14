package com.hanbit.there.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.there.api.domain.Airline;
import com.hanbit.there.api.repo.AirlineRepository;

@Service
public class AirlineService {

	@Autowired
	private AirlineRepository airlineRepository;

	public Airline getAirline(String id) {
		return airlineRepository.findOne(id);
	}

}
