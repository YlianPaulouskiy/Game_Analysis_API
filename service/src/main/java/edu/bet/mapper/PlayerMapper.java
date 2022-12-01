package edu.bet.mapper;

import edu.bet.dto.PlayerDto;
import edu.bet.dto.PlayerTeamDto;
import edu.bet.entity.Player;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface PlayerMapper {

    Player convert(PlayerDto playerDto);

    PlayerDto convert(Player player);

    PlayerTeamDto convertToDto(Player player);

    Player convertToModel(PlayerTeamDto playerTeamDto);

    @AfterMapping
    default void linkTeam(@MappingTarget Player player) {
        if (player.getTeam() != null
                && player.getTeam().getPlayers() != null) {
            player.getTeam().getPlayers().add(player);
        }
    }

}
