package com.olx.advertise.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.olx.advertise.config.MasterDataClient;
import com.olx.advertise.model.ADVStatus;
import com.olx.advertise.model.Advertises;
import com.olx.advertise.model.Category;
import com.olx.advertise.service.AdvertiseService;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RefreshScope
public class AdvertiseController {

	@Autowired
	private AdvertiseService advService;
	
	@Autowired
	private MasterDataClient MDClient;
	
	@GetMapping("/advertise")
	public List<Advertises> getAllAdvertises(){
		return advService.getAllAdvertise();
	}
	
	public Advertises handleException(Advertises adv, Exception ex) {
		Advertises fallBackAdv = new Advertises();
		fallBackAdv.setTitle("FALL BACK ADVERTISE");
		fallBackAdv.setDescription(ex.getMessage());
		return fallBackAdv;
	}
	
	@PostMapping("/advertise")
	@Retry(name = "olx-advertise-api", fallbackMethod = "handleException")
	public Advertises insertAdvertise(@RequestBody Advertises adv) {
		adv.setCreatedDate(new Date());
		adv.setModifiedDate(new Date());
		adv.setPostedBy("Ram");
		adv.setUsername("java_ms");
		Category cat = MDClient.getCategory(adv.getCategoryId());
		ADVStatus advStatus = MDClient.getaAvStatus(adv.getStatus());
		adv.setCategory(cat.getName());
		adv.setStatus(advStatus.getStatus());
		return advService.insertAdvertise(adv);
	}
	
	public Advertises handleError(int id, Advertises adv, Exception ex) {
		Advertises fallBackAdv = new Advertises();
		fallBackAdv.setTitle("FALL BACK ADVERTISE");
		fallBackAdv.setDescription(ex.getMessage());
		return fallBackAdv;
	}
	
	@PutMapping("/advertise/{id}")
	@Retry(name = "olx-advertise-update-api", fallbackMethod = "handleError")
	public Advertises updateAdvertises(@PathVariable int id, @RequestBody Advertises adv) {
		Advertises updAdv = advService.getAdvertiseById(id);
		updAdv.setTitle(adv.getTitle());
		updAdv.setCategory(adv.getCategory());
		updAdv.setPrice(adv.getPrice());
		updAdv.setStatus(adv.getStatus());
		updAdv.setDescription(adv.getDescription());
		updAdv.setModifiedDate(new Date());
		Category cat = MDClient.getCategory(adv.getCategoryId());
		ADVStatus advStatus = MDClient.getaAvStatus(adv.getStatus());
		updAdv.setCategory(cat.getName());
		updAdv.setStatus(advStatus.getStatus());

		return advService.updateAdvertiseById(updAdv);
	}
	
	@GetMapping("/advertise/{id}")
	public Advertises getAdvertiseById(@PathVariable int id) {
		return advService.getAdvertiseById(id);
	}
	
	@DeleteMapping(value = "/advertise/{id}")
	public boolean deleteAdvertiseById(@PathVariable int id) {
		try {
			advService.deleteAdvertise(id);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
