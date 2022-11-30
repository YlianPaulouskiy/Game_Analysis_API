package edu.bet.repository;

import edu.bet.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MapRepository extends JpaRepository<Map, String> {

    Optional<Map> findByNameAndTeamName(String map, String teamName);

    void deleteByNameAndTeamName(String map, String teamName);

    boolean existsByNameAndTeamName(String map, String teamName);

}
