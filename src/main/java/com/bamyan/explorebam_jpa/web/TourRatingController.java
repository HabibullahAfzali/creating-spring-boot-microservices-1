package com.bamyan.explorebam_jpa.web;

import com.bamyan.explorebam_jpa.model.TourRating;
import com.bamyan.explorebam_jpa.service.TourRatingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
	@Autowired
	private TourRatingService tourRatingService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTourRating(@PathVariable(value = "tourId") int tourId,
	                             @RequestBody @Valid RatingDto ratingDto) {
		log.info("POST /tours/{}/ratings ", tourId);
				tourRatingService.createNew(tourId,
				ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());
	}
	@GetMapping
	public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId){
		log.info("GET /tours/{}/ratings", tourId);
		List<TourRating> tourRatings = tourRatingService.lookupRatings(tourId);

		return tourRatings.stream().map(RatingDto::new).toList();

	}
	@GetMapping("/average")

	public Map<String ,Double> getAverage(@PathVariable(value = "tourId") int tourId){
		log.info("GET /tours/{}/ratings/average", tourId);
		return Map.of("average",tourRatingService.getAverageScore(tourId));

	}

	/**
	 * Update score and comment of a Tour Rating
	 *
	 * @param tourId
	 * @param ratingDto
	 * @return The modified Rating DTO.
	 */
	@PutMapping
	public RatingDto updateWithPut(@PathVariable(value = "tourId") int tourId, @RequestBody @Valid RatingDto ratingDto) {
		log.info("PUT /tours/{}/ratings", tourId);
		return new RatingDto(tourRatingService.update(tourId, ratingDto.getCustomerId(),
				ratingDto.getScore(), ratingDto.getComment()));
	}

	@PatchMapping
	public RatingDto updateWithPatch(@PathVariable(value = "tourId") int tourId,
		@RequestBody @Valid RatingDto ratingDto){
		log.info("PATCH /tours/{}/ratings", tourId);
		return  new RatingDto(tourRatingService.updateSome(tourId,ratingDto.getCustomerId(),
				Optional.ofNullable(ratingDto.getScore()),
				Optional.ofNullable(ratingDto.getComment())));
	}

	/**
	 * Delete a Rating of a tour made by a customer
	 *
	 * @param tourId
	 * @param customerId
	 */
	@DeleteMapping("/{customerId}")
	public void delete(@PathVariable(value = "tourId") int tourId, @PathVariable(value = "customerId") int customerId) {
		log.info("DELETE /tours/{}/ratings/{}", tourId, customerId);
		tourRatingService.delete(tourId, customerId);
	}

	/**
	 * Create Several Tour Ratings for one tour, score and several customers.
	 *
	 * @param tourId
	 * @param score
	 * @param customers
	 */
	@PostMapping("/batch")
	@ResponseStatus(HttpStatus.CREATED)
	public void createManyTourRatings(@PathVariable(value = "tourId") int tourId,
	                                  @RequestParam(value = "score") int score,
	                                  @RequestBody List<Integer> customers) {
		log.info("POSt /tours/{}/ratings/batch", tourId);
		tourRatingService.rateMany(tourId, score, customers);
	}

}
