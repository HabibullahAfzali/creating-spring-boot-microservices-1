package com.example.explorcali_pa.repo;

import com.example.explorcali_pa.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour,Integer> {

}
