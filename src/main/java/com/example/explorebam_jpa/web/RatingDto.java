package com.example.explorebam_jpa.web;

import com.example.explorebam_jpa.model.TourRating;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
	@Min(0)
	@Max(5)
	private Integer score;
	@Size(max = 255)
	private String comment;
	@NotNull
	private Integer customerId;

	public RatingDto(TourRating entity) {
		this.score = entity.getScore();
		this.comment = entity.getComment();
		this.customerId = entity.getCustomerId();
	}
}
