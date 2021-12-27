package com.olx.advertise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.advertise.model.Advertises;
import com.olx.advertise.repository.AdvertisesRepository;

@Service
public class AdvertiseService {

	@Autowired
	private AdvertisesRepository advRepo;
	
	public List<Advertises> getAllAdvertise(){
		return advRepo.findAll();
	}
	
	public Advertises getAdvertiseById(int id) {
		return advRepo.findById(id).get();
	}
	
	public Advertises updateAdvertiseById(Advertises adv) {
		return advRepo.save(adv);
	}
	
	public Advertises insertAdvertise(Advertises adv) {
		return advRepo.save(adv);
	}
	
	public void deleteAdvertise(int id) {
		advRepo.deleteById(id);
	}
	
}
