package edu.bet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchWinDto extends MatchDto {

    private String prediction;
    private String winner;
}
