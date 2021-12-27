package com.olx.masterdata.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.olx.masterdata.model.ADVStatus;
import com.olx.masterdata.model.Category;
import com.olx.masterdata.repository.ADVStatusRepository;
import com.olx.masterdata.repository.CategoryRepository;

@Component
public class MasterDataLoader implements ApplicationRunner {

	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private ADVStatusRepository advStatusRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		catRepo.save(new Category(1L,"Furniture","MasterData  Furniture"));
		catRepo.save(new Category(2L,"Cars","MasterData of Cars"));
		catRepo.save(new Category(3L,"Mobiles","MasterData of Mobiles"));
		catRepo.save(new Category(4L,"Real Estate","MasterData of Real Estate"));
		catRepo.save(new Category(5L,"Sports","MasterData of Sports"));
		
		advStatusRepo.save(new ADVStatus(1L,"OPEN"));
		advStatusRepo.save(new ADVStatus(2L,"CLOSED"));
		
		
	}

}
