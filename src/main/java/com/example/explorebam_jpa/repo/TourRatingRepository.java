package com.example.explorebam_jpa.repo;

import com.example.explorebam_jpa.model.Tour;
import com.example.explorebam_jpa.model.TourRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends JpaRepository<TourRating,Integer>, CrudRepository<TourRating,Integer> {

	List<TourRating> findByTourId(Integer tourId);

	Optional<TourRating> findByTourIdAndCustomerId(Integer tourId, Integer customerId);
}
