package com.olx.advertise.config;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.olx.advertise.model.ADVStatus;
import com.olx.advertise.model.Category;

@FeignClient("GATEWAY-SERVICE")
public interface MasterDataClient {

	@GetMapping("/masterdata/category/{catId}")
	Category getCategory(@PathVariable("catId") int catId);
	
	@GetMapping("/masterdata/adv-status/{statusId}")
	ADVStatus getaAvStatus(@PathVariable("statusId") String statusId);
}
