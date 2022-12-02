package edu.bet.service;

import edu.bet.dto.MatchDto;
import edu.bet.dto.MatchWinDto;
import edu.bet.entity.Match;
import edu.bet.mapper.MatchMapper;
import edu.bet.prediction.Prediction;
import edu.bet.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final Prediction prediction;

    public MatchWinDto getMatchById(Long id) {
        return matchMapper.convertToDto(matchRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Match " + id + " not found.")
        ));
    }

    public List<MatchWinDto> getAllMatches() {
        List<MatchWinDto> matchDtoList = new ArrayList<>();
        for (Match match : matchRepository.findAll()) {
            matchDtoList.add(matchMapper.convertToDto(match));
        }
        return matchDtoList;
    }

    public MatchWinDto getMatchByTeam(String teamName) {
        return matchMapper
                .convertToDto(matchRepository
                        .findMatchByTeam(teamName).orElseThrow(
                                () -> new EntityNotFoundException("Match with team " + teamName + " not found.")
                        )
                );
    }

    // FIXME: 29.11.2022 добавить логику предсказания победителя и устанавливать ее в модель.
    public MatchWinDto save(MatchDto match) {
        MatchWinDto matchWinDto = matchMapper.convertToDto(matchMapper.convert(match));
        matchWinDto.setPrediction(prediction.getWinner(matchMapper.convert(match)).getName());
        return matchMapper.convertToDto(matchRepository.save(matchMapper.convertToModel(matchWinDto)));
    }

    public MatchWinDto addWinner(Long matchId, String winner) {
        Match match = matchMapper.convertToModel(getMatchById(matchId));
        match.setWinner(winner);
        matchRepository.save(match);
        return matchMapper.convertToDto(match);
    }

    public void remove(Long matchId) {
        if (matchRepository.existsById(matchId)) {
            matchRepository.deleteById(matchId);
        } else  {
            throw new EntityNotFoundException("Match " + matchId + " not found.");
        }
    }
}
