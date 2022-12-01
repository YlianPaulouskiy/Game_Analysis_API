package edu.bet.service;

import edu.bet.dto.MapDto;
import edu.bet.dto.MapTeamDto;
import edu.bet.entity.Map;
import edu.bet.entity.Team;
import edu.bet.mapper.MapMapper;
import edu.bet.repository.MapRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MapService {

    private final MapMapper mapMapper;
    private final MapRepository mapRepository;


    public MapTeamDto findByName(String mapName, String teamName) {
        return mapMapper.convertToDto(
                mapRepository.findByNameAndTeamName(mapName, teamName).orElseThrow(
                        () -> new EntityNotFoundException("Map " + mapName + " of " + teamName + " team not found.")
                )
        );
    }


    public List<MapTeamDto> findAll() {
        List<MapTeamDto> mapTeamDtoList = new ArrayList<>();
        for (Map map : mapRepository.findAll()) {
            mapTeamDtoList.add(mapMapper.convertToDto(map));
        }
        return mapTeamDtoList;
    }

    /**Сохраняем карты в бд, если она там уже была то оставляем
     * ей ссылку на команду на которую она ссылалась и
     *
     * @param mapDto
     * @return
     */
    public MapTeamDto save(@Valid MapDto mapDto) {
        if (mapRepository.existsByNameAndTeamName(mapDto.getName(), mapDto.getTeamName())) {
            //получаем карту из БД с ссылкой на команду
            Map map = mapRepository.findByNameAndTeamName(mapDto.getName(), mapDto.getTeamName()).get();
            //сохраняет команду в памяти
            Team team = map.getTeam();
            // перезаписываем поля карты
            map = mapMapper.convert(mapDto);
            // если команда уже была привязана то привязываем ее еще раз
            if (team != null) {
                map.setTeam(team);
            }
            return mapMapper.convertToDto(mapRepository.save(map));
        } else {
            return mapMapper.convertToDto(
                    mapRepository.save(
                            mapMapper.convert(mapDto)
                    )
            );
        }
    }


    public void remove(String mapName, String teamName) {
        if (mapRepository.existsByNameAndTeamName(mapName, teamName)) {
            mapRepository.deleteByNameAndTeamName(mapName, teamName);
        } else {
            throw new EntityNotFoundException("Map " + mapName + " of " + teamName + " team not found.");
        }
    }
}
