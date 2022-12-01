package edu.bet.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class MatchDto {

    @Positive
    private Long id;
    @NotBlank
    private String teamName1;
    @NotBlank
    private String teamName2;
    private String firstMap;
    private String secondMap;
    private String thirdMap;
}
