package edu.bet.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MapTeamDto extends MapDto {

    List<TeamDto> teamList;

}
