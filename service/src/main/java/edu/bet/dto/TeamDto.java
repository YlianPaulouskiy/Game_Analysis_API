package edu.bet.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@EqualsAndHashCode
public class TeamDto {

    @NotBlank(message = "Invalid nickName sources")
    private String name;
    @Positive
    private Integer rating;
    @Max(value = 1)
    @Min(value = 0)
    private Double winRate;

}
