package edu.bet.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamPlayerDto extends TeamDto {

    List<PlayerDto> players;
    List<MapDto> mapPull;

}
