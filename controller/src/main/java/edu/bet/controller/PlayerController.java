package edu.bet.controller;

import edu.bet.dto.PlayerDto;
import edu.bet.dto.PlayerTeamDto;
import edu.bet.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/get")
    public PlayerTeamDto findOne(@NotBlank @RequestParam String nickName) {
        return playerService.findByName(nickName);
    }

    @GetMapping("/getAll")
    public List<PlayerTeamDto> findAll() {
        return playerService.findAll();
    }

    @PostMapping("/save")
    public PlayerTeamDto save(@Valid @RequestBody PlayerDto playerDto) {
        return playerService.save(playerDto);
    }

    @DeleteMapping("/remove")
    public void remove(@NotBlank @RequestParam String nickName) {
        playerService.remove(nickName);
    }

}
