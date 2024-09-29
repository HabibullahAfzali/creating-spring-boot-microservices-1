package com.example.explorebam_jpa.web;

import com.example.explorebam_jpa.service.TourRatingService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

	private TourRatingService tourRatingService;

}
