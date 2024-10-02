package com.bamyan.explorebam_jpa.service;

import com.bamyan.explorebam_jpa.model.TourPackage;
import com.bamyan.explorebam_jpa.repo.TourPackageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class TourPackageService {
	@Autowired
	private final TourPackageRepository tourPackageRepository;

	public TourPackage createTourPackage(String code, String name){
		log.info("Create tour package {}:{}",code, name);
		return tourPackageRepository.findById(code)
				.orElse(tourPackageRepository.save(new TourPackage(code,name)));

	}
	public List<TourPackage> lookupAll(){
		log.info("Lookup all");
		return  tourPackageRepository.findAll();
	}
	public long total(){
		log.info("Get total tour package");
		return  tourPackageRepository.count();
	}
}
