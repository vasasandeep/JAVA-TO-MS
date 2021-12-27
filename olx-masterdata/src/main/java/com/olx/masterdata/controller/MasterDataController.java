package com.olx.masterdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.masterdata.model.ADVStatus;
import com.olx.masterdata.model.Category;
import com.olx.masterdata.service.MasterDataService;

@RestController
@RequestMapping(value = "/masterdata")
public class MasterDataController {

	@Autowired
	private MasterDataService MDService;
	
	@GetMapping("/category")
	public List<Category> getAllCategories(){
		return MDService.getAllCategories();
	}
	
	@GetMapping("/category/{id}")
	public Category getCategory(@PathVariable long id){
		return MDService.getCategory(id);
	}
	
	@GetMapping("/adv-status")
	public List<ADVStatus> getAllAdvStatus(){
		return MDService.getAllAdvStatus();
	}
	
	@GetMapping("/adv-status/{id}")
	public ADVStatus getAdvStatus(@PathVariable long id){
		return MDService.getAdvStatus(id);
	}
}
