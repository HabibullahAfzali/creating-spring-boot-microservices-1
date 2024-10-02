package com.bamyan.explorebam_jpa.service;

import com.bamyan.explorebam_jpa.model.Difficulty;
import com.bamyan.explorebam_jpa.model.Region;
import com.bamyan.explorebam_jpa.model.Tour;
import com.bamyan.explorebam_jpa.model.TourPackage;
import com.bamyan.explorebam_jpa.repo.TourPackageRepository;
import com.bamyan.explorebam_jpa.repo.TourRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class TourService {
	private final TourPackageRepository tourPackageRepository;
	private final TourRepository tourRepository;

	public Tour createTour(String tourPackageName, String title,
	                       String description, String blurb, Integer price, String duration,
	                       String bullets, String keywords, Difficulty difficulty, Region region) {
		log.info("Create tour {} for package {}", title, tourPackageName);
		TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(()->new RuntimeException("Tour" +
				" Package not found for id "+tourPackageName));
		return tourRepository.save( new Tour(title, description, blurb,
				price, duration, bullets, keywords, tourPackage, difficulty, region));
	}

	public List<Tour> lookupByDifficulty(Difficulty difficulty){
		log.info("Lookup tours by difficulty {}", difficulty);
		return tourRepository.findByDifficulty(difficulty);

	}
	public List<Tour> lookupByPackage(String tourPackageCode){
		log.info("Lookup tour by code {}", tourPackageCode);
		return tourRepository.findByTourPackageCode(tourPackageCode);
	}

	public long total() {
		log.info("Get total tours");
		return tourRepository.count();
	}
}
