package com.olx.masterdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.masterdata.model.ADVStatus;
import com.olx.masterdata.model.Category;
import com.olx.masterdata.repository.ADVStatusRepository;
import com.olx.masterdata.repository.CategoryRepository;

@Service
public class MasterDataService {

	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private ADVStatusRepository advStatusRepo;
	
	public List<Category> getAllCategories(){
		return catRepo.findAll();
	}
	
	public Category getCategory(long id){
		return catRepo.findById(id).get();
	}
	
	public List<ADVStatus> getAllAdvStatus(){
		return advStatusRepo.findAll();
	}
	
	public ADVStatus getAdvStatus(long id){
		return advStatusRepo.findById(id).get();
	}
}
