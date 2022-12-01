package edu.bet.service;

import edu.bet.dto.PlayerDto;
import edu.bet.dto.PlayerTeamDto;
import edu.bet.entity.Player;
import edu.bet.entity.Team;
import edu.bet.mapper.PlayerMapper;
import edu.bet.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerTeamDto findByName(String nickName) {
        return playerMapper.convertToDto(playerRepository.findByNickName(nickName)
                .orElseThrow(() -> new EntityNotFoundException("Player " + nickName + " not found."))
        );
    }

    public List<PlayerTeamDto> findAll() {
        List<PlayerTeamDto> playerTeamDtoList = new ArrayList<>();
        for (Player player : playerRepository.findAll()) {
            playerTeamDtoList.add(playerMapper.convertToDto(player));
        }
        return playerTeamDtoList;
    }

    public List<PlayerTeamDto> findAllByTeam(String teamName) {
        List<PlayerTeamDto> playerTeamDtoList = new ArrayList<>();
        for (Player player : playerRepository.findPlayersByTeamName(teamName)) {
            playerTeamDtoList.add(playerMapper.convertToDto(player));
        }
        return playerTeamDtoList;
    }

    // TODO: 30.11.2022 сохранять с сохранением команды, в которой состоит игрок
    public PlayerTeamDto save(PlayerDto playerDto) {
        if (playerRepository.existsByNickName(playerDto.getNickName())) {
            // получаем игрока из БД
            Player player = playerRepository.findByNickName(playerDto.getNickName()).get();
            // сохраняем команду в которой он состоит
            Team team = player.getTeam();
            // перезаписываем данные игроку
            player = playerMapper.convert(playerDto);
            // если состоял в команде перезаписываем эту же команду
            if (team != null) {
                player.setTeam(team);
            }
            return playerMapper.convertToDto(playerRepository.save(player));
        } else {
            return playerMapper.convertToDto(
                    playerRepository.save(
                            playerMapper.convert(playerDto)
                    )
            );
        }
    }

    public void remove(String nickName) {
        if (playerRepository.existsByNickName(nickName)) {
            playerRepository.delete(playerMapper.convert(findByName(nickName)));
        } else {
            throw new EntityNotFoundException("Player " + nickName + "not exist.");
        }
    }

}
