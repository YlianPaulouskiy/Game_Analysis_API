package edu.bet.controller;

import edu.bet.dto.MapDto;
import edu.bet.dto.MapTeamDto;
import edu.bet.service.MapService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/maps")
public class MapController {

    private final MapService mapService;

    @GetMapping("/get")
    public MapTeamDto findOne(@NotBlank @RequestParam String mapName,
                                 @NotBlank @RequestParam String teamName) {
        return mapService.findByName(mapName,teamName);
    }

    @GetMapping("/getAll")
    public List<MapTeamDto> findAll() {
        return mapService.findAll();
    }

    @PostMapping("/save")
    public MapTeamDto save(@Valid @RequestBody MapDto mapDto) {
        return mapService.save(mapDto);
    }

    @DeleteMapping("/remove")
    public void remove(@NotBlank @RequestParam String mapName,
                       @NotBlank @RequestParam String teamName) {
        mapService.remove(mapName, teamName);
    }
}
