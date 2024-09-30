package com.bamyan.explorebam_jpa.web;

import com.bamyan.explorebam_jpa.model.TourRating;
import com.bamyan.explorebam_jpa.service.TourRatingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
	@Autowired
	private TourRatingService tourRatingService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTourRating(@PathVariable(value = "tourId") int tourId,
	                             @RequestBody @Valid RatingDto ratingDto) {
				tourRatingService.createNew(tourId,
				ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());
	}
	@GetMapping
	public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId){
		List<TourRating> tourRatings = tourRatingService.lookupRatings(tourId);

		return tourRatings.stream().map(RatingDto::new).toList();

	}
	@GetMapping("/average")

	public Map<String ,Double> getAverage(@PathVariable(value = "tourId") int tourId){

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
		return new RatingDto(tourRatingService.update(tourId, ratingDto.getCustomerId(),
				ratingDto.getScore(), ratingDto.getComment()));
	}

	/**
	 * Delete a Rating of a tour made by a customer
	 *
	 * @param tourId
	 * @param customerId
	 */
	@DeleteMapping("/{customerId}")
	public void delete(@PathVariable(value = "tourId") int tourId, @PathVariable(value = "customerId") int customerId) {
		tourRatingService.delete(tourId, customerId);
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String return404(NoSuchElementException exception) {
		return exception.getMessage();
	}


}
