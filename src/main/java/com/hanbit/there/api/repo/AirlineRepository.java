package com.hanbit.there.api.repo;

import org.springframework.data.repository.CrudRepository;

import com.hanbit.there.api.domain.Airline;

public interface AirlineRepository extends CrudRepository<Airline, String> {

}
