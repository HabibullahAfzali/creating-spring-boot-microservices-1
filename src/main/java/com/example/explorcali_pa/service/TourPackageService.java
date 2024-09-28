package com.example.explorcali_pa.service;

import com.example.explorcali_pa.model.TourPackage;
import com.example.explorcali_pa.repo.TourPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourPackageService {
	private TourPackageRepository tourPackageRepository;


	public TourPackageService(TourPackageRepository tourPackageRepository) {
		this.tourPackageRepository = tourPackageRepository;
	}
	public TourPackage createTourPackage(String code, String name){
		return new TourPackage(code,name);

	}
	public List<TourPackage> lookupAll(){
		return  null;
	}
	public long total(){

		return  0;
	}
}
