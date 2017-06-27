package com.piggymetrics.bike.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.piggymetrics.bike.BikeApplication;
import com.piggymetrics.bike.domain.BikeInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BikeApplication.class)
public class BikeRepositoryTest {

	@Autowired
	private SharedBikeRepository repository;

	@Test
	public void shouldFindAccountByName() {

		BikeInfo stub = getStubBikeInfo();
		repository.save(stub);

		BikeInfo found = repository.findByBikeId(stub.getBikeId());
		assertEquals(stub.getLat(), found.getLat());
		assertEquals(stub.getLon(), found.getLon());
	}

	private BikeInfo getStubBikeInfo() {

		BikeInfo bike = new BikeInfo();
		bike.setBikeId("test");
		bike.setLat("1111");
		bike.setLon("1211");
		return bike;
	}
}
