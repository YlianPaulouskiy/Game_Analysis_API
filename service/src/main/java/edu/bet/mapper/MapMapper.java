package edu.bet.mapper;

import edu.bet.dto.MapDto;
import edu.bet.dto.MapTeamDto;
import edu.bet.entity.Map;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface MapMapper {

    Map convert(MapDto mapDto);

    MapDto convert(Map map);

    MapTeamDto convertToDto(Map map);

    Map convertToModel(MapTeamDto mapTeamDto);

    @AfterMapping
    default void linkTeams(@MappingTarget Map map) {
        if (map.getTeamList() != null ) {
            map.getTeamList()
                    .stream()
                    .filter(team -> team.getMapPull()!=null)
                    .forEach(team -> team.getMapPull().add(map));
        }
    }

}
