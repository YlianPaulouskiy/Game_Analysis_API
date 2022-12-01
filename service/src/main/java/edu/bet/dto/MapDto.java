package edu.bet.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@EqualsAndHashCode
public class MapDto {

    @NotBlank(message = "Invalid name sources")
    private String name;
    @Positive
    private Double winRate;
    @NotBlank(message = "Invalid teamName sources")
    private String teamName;

}
