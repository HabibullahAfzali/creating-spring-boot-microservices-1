package com.example.explorebam_jpa.web;

import com.example.explorebam_jpa.model.TourRating;
import com.example.explorebam_jpa.service.TourRatingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

	private TourRatingService tourRatingService;

	public RatingDto createTourRating(@PathVariable(value = "tourId") int tourId
	, @RequestBody @Valid RatingDto ratingDto){

		TourRating rating = tourRatingService.createNew(tourId,ratingDto.getCustomerId(),
				ratingDto.getScore(), ratingDto.getComment());

		return new RatingDto(rating);

	}

}
