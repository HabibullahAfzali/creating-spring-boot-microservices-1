package com.bamyan.explorebam_jpa.repo;

import com.bamyan.explorebam_jpa.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "packages",collectionResourceRel ="packages")
public interface TourPackageRepository extends JpaRepository<TourPackage,String> {


	Optional<TourPackage> findByName(String name);
}
