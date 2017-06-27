package com.piggymetrics.bike.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.piggymetrics.bike.domain.BikeInfo;
import com.piggymetrics.bike.service.SharedBikeService;

@RestController
public class SharedBikeController {

	@Autowired
	private SharedBikeService sharedBikeService;

	@PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
	@RequestMapping(path = "/{bikeId}", method = RequestMethod.GET)
	public BikeInfo getInfoById(@PathVariable String bikeId) {
		return sharedBikeService.findByBikeId(bikeId);
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public BikeInfo addBike(@Valid @RequestBody BikeInfo bike) {
		return sharedBikeService.add(bike);
	}

}
