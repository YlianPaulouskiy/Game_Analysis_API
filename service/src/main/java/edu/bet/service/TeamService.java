package edu.bet.service;

import edu.bet.dto.TeamDto;
import edu.bet.dto.TeamPlayerDto;
import edu.bet.entity.Map;
import edu.bet.entity.Player;
import edu.bet.entity.Team;
import edu.bet.mapper.TeamMapper;
import edu.bet.repository.MapRepository;
import edu.bet.repository.PlayerRepository;
import edu.bet.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final MapRepository mapRepository;
    private final TeamMapper teamMapper;

    public TeamPlayerDto findByName(String timeName) {
        return teamMapper.convertToDto(teamRepository.findByName(timeName)
                .orElseThrow(() -> new EntityNotFoundException("Team " + timeName + " not found."))
        );
    }

    // FIXME: 30.11.2022 может быть ошибка при получении пустого массива и перебора его
    public List<TeamPlayerDto> findAll() {
        List<TeamPlayerDto> teamPlayerDtoList = new ArrayList<>();
        for (Team team : teamRepository.findAll()) {
            teamPlayerDtoList.add(teamMapper.convertToDto(team));
        }
        return teamPlayerDtoList;
    }

    /** Сохраняет команду в БД, если такая команда
     * уже существует то просто перезаписывает поля
     * для предсказаний, сохраняя состав команды
     *
     * @param teamDto команда с параметрами
     * @return команда с составом
     */
    public TeamPlayerDto save(TeamDto teamDto) {
        if (teamRepository.existsByName(teamDto.getName())) {
            //тим из БД
            Team team = teamRepository.findByName(teamDto.getName()).get();
            //игроки  команды
            List<Player> playerList = team.getPlayers();
            //новая тим, с новыми параметрами
            team = teamMapper.convert(teamDto);
            //ставим старых игроков
            if (!playerList.isEmpty()) {
                team.setPlayers(playerList);
            }
            return teamMapper.convertToDto(teamRepository.save(team));
        } else {
            return teamMapper.convertToDto(
                    teamRepository.save(
                            teamMapper.convert(teamDto)
                    )
            );
        }
    }

    /**
     * Проверку на отсутствие игрока у других команд
     * сохранить команду с новым игроком через репозит
     * и сохранить игрока с новой командой через репозит
     *
     * @param teamName   название команды
     * @param playerName ник игрока
     * @return команду с новым игроком
     */
    public TeamPlayerDto addPlayer(String teamName, String playerName) {
        if (teamRepository.existsByName(teamName)
                && playerRepository.existsByNickName(playerName)) {
            Team team = teamRepository.findByName(teamName).get();
            Player player = playerRepository.findByNickName(playerName).get();
            for (Team teams : teamRepository.findAll()) {
                // если не эта команда и содержит этого игрока
                if (!teams.getName().equals(teamName)
                        && teams.getPlayers().contains(player)) {
                    throw new EntityExistsException("Player exists at another team.");
                }
            }
            if (team.getPlayers().size() == 5) {
                throw new RuntimeException("Team if Full");
            } else {
                team.getPlayers().add(player);
                player.setTeam(team);
            }
            teamRepository.save(team);
            playerRepository.save(player);
            return teamMapper.convertToDto(team);
        } else {
            throw new EntityNotFoundException("Team" + teamName + " or Player " + playerName + " not found");
        }
    }

    /**
     * Добавляет карту с винрейтом команде
     *
     * @param teamName название команды
     * @param mapName  название карты
     * @return команду с картой
     */
    public TeamPlayerDto addMap(String teamName, String mapName) {
        if (teamRepository.existsByName(teamName)
                && mapRepository.existsByNameAndTeamName(mapName, teamName)) {
            Team team = teamRepository.findByName(teamName).get();
            Map map = mapRepository.findByNameAndTeamName(mapName, teamName).get();
            if (!team.getMapPull().contains(map)
                    && map.getTeamName().equals(teamName)) {
                team.getMapPull().add(map);
                map.setTeam(team);
            }
            teamRepository.save(team);
            mapRepository.save(map);
            return teamMapper.convertToDto(team);
        } else {
            throw new EntityNotFoundException("Team" + teamName + " or Map " + mapName + " not found");
        }
    }

    /** Удаляет команду из БД
     *
     * @param teamName название команды
     */
    public void remove(String teamName) {
        if (teamRepository.existsByName(teamName)) {
            teamRepository.deleteByName(teamName);
        } else {
            throw new EntityNotFoundException("Team " + teamName + " not found.");
        }
    }
}
