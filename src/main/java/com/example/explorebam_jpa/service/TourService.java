package com.example.explorebam_jpa.service;

import com.example.explorebam_jpa.model.Difficulty;
import com.example.explorebam_jpa.model.Region;
import com.example.explorebam_jpa.model.Tour;
import com.example.explorebam_jpa.model.TourPackage;
import com.example.explorebam_jpa.repo.TourPackageRepository;
import com.example.explorebam_jpa.repo.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

	private TourPackageRepository tourPackageRepository;
	private TourRepository tourRepository;

	public TourService(TourRepository tourRepository,TourPackageRepository tourPackageRepository){
		this.tourPackageRepository = tourPackageRepository;
		this.tourRepository = tourRepository;
	}
	public Tour createTour(String tourPackageName, String title,
	                       String description, String blurb, Integer price, String duration,
	                       String bullets, String keywords, Difficulty difficulty, Region region) {

		TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(()->new RuntimeException("Tour" +
				" Package not found for id "+tourPackageName));
		return tourRepository.save( new Tour(title, description, blurb,
				price, duration, bullets, keywords, tourPackage, difficulty, region));
	}

	public List<Tour> lookupByDifficulty(Difficulty difficulty){
		return tourRepository.findByDifficulty(difficulty);

	}
	public List<Tour> lookupByPackage(String tourPackageCode){

		return tourRepository.findByTourPackageCode(tourPackageCode);
	}

	public long total() {
		return tourRepository.count();
	}
}
