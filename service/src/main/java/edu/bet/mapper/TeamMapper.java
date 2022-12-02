package edu.bet.mapper;

import edu.bet.dto.TeamDto;
import edu.bet.dto.TeamPlayerDto;
import edu.bet.entity.Team;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface TeamMapper {

    Team convert(TeamDto teamDto);

    TeamDto convert(Team team);

    TeamPlayerDto convertToDto(Team team);

    Team convertToModel(TeamPlayerDto teamPlayerDto);

    @AfterMapping
    default void linkPlayer(@MappingTarget Team team) {
        if (team.getPlayers() != null) {
            team.getPlayers().forEach(player -> player.setTeam(team));
        }
    }

    @AfterMapping
    default void linkMapPull(@MappingTarget Team team) {
        if (team.getMapPull() != null) {
            team.getMapPull().forEach(map -> map.setTeam(team));
        }
    }


}
