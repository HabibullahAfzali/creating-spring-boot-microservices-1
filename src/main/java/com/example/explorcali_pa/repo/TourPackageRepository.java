package com.example.explorcali_pa.repo;

import com.example.explorcali_pa.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourPackageRepository extends JpaRepository<TourPackage,String> {


	Optional<TourPackage> findByName(String name);
}
