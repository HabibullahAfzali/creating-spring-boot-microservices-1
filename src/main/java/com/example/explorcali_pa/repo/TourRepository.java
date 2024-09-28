package com.example.explorcali_pa.repo;

import com.example.explorcali_pa.model.Difficulty;
import com.example.explorcali_pa.model.Tour;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour,Integer> {
List<Tour> findByDifficulty(Difficulty difficulty);
List<Tour> findByTourPackageCode(String code);
}
