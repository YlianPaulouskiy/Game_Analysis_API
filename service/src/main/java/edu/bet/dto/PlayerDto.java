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
public class PlayerDto {

    @NotBlank(message = "Invalid nickName sources")
    private String nickName;
    @Positive
    private Double rating;
    @Positive
    private Double kd;
    @Max(value = 1)
    @Min(value = 0)
    private Double kr;



}
