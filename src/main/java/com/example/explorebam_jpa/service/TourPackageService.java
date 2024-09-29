package com.example.explorebam_jpa.service;

import com.example.explorebam_jpa.model.TourPackage;
import com.example.explorebam_jpa.repo.TourPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourPackageService {
	private TourPackageRepository tourPackageRepository;


	public TourPackageService(TourPackageRepository tourPackageRepository) {
		this.tourPackageRepository = tourPackageRepository;
	}
	public TourPackage createTourPackage(String code, String name){
		return tourPackageRepository.findById(code)
				.orElse(tourPackageRepository.save(new TourPackage(code,name)));

	}
	public List<TourPackage> lookupAll(){
		return  tourPackageRepository.findAll();
	}
	public long total(){

		return  tourPackageRepository.count();
	}
}
