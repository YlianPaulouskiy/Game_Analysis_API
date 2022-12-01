package edu.bet.mapper;


import edu.bet.dto.MatchDto;
import edu.bet.dto.MatchWinDto;
import edu.bet.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface MatchMapper {

    Match convert(MatchDto matchDto);

    MatchDto convert(Match match);

    MatchWinDto convertToDto(Match match);

    Match convertToModel(MatchWinDto matchWinDto);

}
