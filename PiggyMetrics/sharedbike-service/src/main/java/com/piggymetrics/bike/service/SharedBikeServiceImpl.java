package com.piggymetrics.bike.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.piggymetrics.bike.domain.BikeInfo;
import com.piggymetrics.bike.repository.SharedBikeRepository;

@Service
public class SharedBikeServiceImpl implements SharedBikeService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SharedBikeRepository repository;

	@Override
	public BikeInfo findByBikeId(String bikeId)
	{
		Assert.hasLength(bikeId);
		return repository.findByBikeId(bikeId);
	}

	
	@Override
	public BikeInfo add(BikeInfo bike)
	{

		BikeInfo existing = repository.findByBikeId(bike.getBikeId());
		Assert.isNull(existing, "bike already exists: " + bike.getBikeId());

		repository.save(bike);

		log.info("new bike has been add: " + bike.getBikeId());

		return bike;
	}

}
