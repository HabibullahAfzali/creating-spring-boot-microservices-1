package com.example.explorebam_jpa.repo;

import com.example.explorebam_jpa.model.Difficulty;
import com.example.explorebam_jpa.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour,Integer> {
List<Tour> findByDifficulty(Difficulty difficulty);
List<Tour> findByTourPackageCode(String code);
}
