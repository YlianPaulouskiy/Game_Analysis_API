package edu.bet.controller;

import edu.bet.dto.TeamDto;
import edu.bet.dto.TeamPlayerDto;
import edu.bet.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/get")
    public TeamPlayerDto findOne(@NotBlank @RequestParam String teamName) {
        return teamService.findByName(teamName);
    }

    @GetMapping("/getAll")
    public List<TeamPlayerDto> findAll() {
        return teamService.findAll();
    }

    @PostMapping("/save")
    public TeamPlayerDto save(@Valid @RequestBody TeamDto teamDto) {
        return teamService.save(teamDto);
    }

    @PutMapping("/add/player")
    public TeamPlayerDto addPlayer(@NotBlank @RequestParam String teamName,
                                   @NotBlank @RequestParam String playerName) {
        return teamService.addPlayer(teamName,playerName);
    }

    @PutMapping("/add/map")
    public  TeamPlayerDto addMap(@NotBlank @RequestParam String teamName,
                                 @NotBlank @RequestParam String mapName) {
        return teamService.addMap(teamName, mapName);
    }

    @DeleteMapping("/remove")
    public void remove(@NotBlank @RequestParam String teamName) {
        teamService.remove(teamName);
    }

}
