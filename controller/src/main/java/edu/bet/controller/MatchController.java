package edu.bet.controller;

import edu.bet.dto.MatchDto;
import edu.bet.dto.MatchWinDto;
import edu.bet.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/get/{id}")
    public MatchWinDto findOne(@Positive @PathVariable Long id) {
        return matchService.getMatchById(id);
    }

    @PutMapping("/get/team/{teamName}")
    public MatchWinDto addPlayer(@NotBlank @PathVariable String teamName) {
        return matchService.getMatchByTeam(teamName);
    }

    @GetMapping("/getAll")
    public List<MatchWinDto> findAll() {
        return matchService.getAllMatches();
    }

    @PostMapping("/save")
    public MatchWinDto save(@Valid @RequestBody MatchDto matchDto) {
        return matchService.save(matchDto);
    }

    @PutMapping("/add/winner/{id}")
    public  MatchWinDto addMap(@Positive @PathVariable Long id, @NotBlank @RequestParam String winner) {
        return matchService.addWinner(id,winner);
    }

    @DeleteMapping("/remove/{matchId}")
    public void remove(@Positive @PathVariable Long matchId) {
        matchService.remove(matchId);
    }

}
