package com.piggymetrics.bike.service;

import com.piggymetrics.bike.domain.BikeInfo;

public interface SharedBikeService {

	
	BikeInfo findByBikeId(String bikeId);

	
	BikeInfo add(BikeInfo bike);

	
}
