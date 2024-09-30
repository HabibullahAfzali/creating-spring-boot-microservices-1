package com.bamyan.explorebam_jpa;

import com.bamyan.explorebam_jpa.service.TourService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
class ExplorebamJpaApplicationTests {
@Autowired
	TourService tourService;
	@Test
	void contextLoads() {
		assertNotNull(tourService);
	}

}
