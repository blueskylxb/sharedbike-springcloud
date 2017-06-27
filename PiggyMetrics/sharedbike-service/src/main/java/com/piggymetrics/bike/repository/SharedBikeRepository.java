package com.piggymetrics.bike.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.piggymetrics.bike.domain.BikeInfo;

@Repository
public interface SharedBikeRepository extends CrudRepository<BikeInfo, String> {

	BikeInfo findByBikeId(String bikeId);

}
