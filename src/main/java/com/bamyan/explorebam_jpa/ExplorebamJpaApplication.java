package com.bamyan.explorebam_jpa;

import com.bamyan.explorebam_jpa.model.Difficulty;
import com.bamyan.explorebam_jpa.model.Region;
import com.bamyan.explorebam_jpa.service.TourPackageService;
import com.bamyan.explorebam_jpa.service.TourService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.bamyan.explorebam_jpa")
@EntityScan(basePackages = "com.bamyan.explorebam_jpa.model")
public class ExplorebamJpaApplication implements CommandLineRunner {

	private final String TOUR_IMPORT_FILE = "ExploreBamyan.json";

	@Autowired
	private TourPackageService tourPackageService;

	@Autowired
	private TourService tourService;

	public static void main(String[] args) {

		SpringApplication.run(ExplorebamJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		createTourAllPackages();
		System.out.println("persisted packages = " + tourPackageService.total());
		createToursFromFile(TOUR_IMPORT_FILE);
		System.out.println("Persisted Tours = " + tourService.total());

	}

	private void createTourAllPackages() {

		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");
	}


	private void createToursFromFile(String tourImportFile) throws IOException {

		TourFromFile.read(tourImportFile).forEach(t ->
				tourService.createTour(
						t.packageName(),
						t.title(),
						t.description(),
						t.blurb(),
						t.price(),
						t.length(),
						t.bullets(),
						t.keywords(),
						Difficulty.valueOf(t.difficulty()),
						Region.findByLabel(t.region())
				)
		);
	}


	record TourFromFile(String packageName, String title, String description, String blurb, Integer price,
	                    String length, String bullets, String keywords, String difficulty, String region) {
		static List<TourFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().readValue(new File(fileToImport), new TypeReference<List<TourFromFile>>() {
			});
		}
	}
}
