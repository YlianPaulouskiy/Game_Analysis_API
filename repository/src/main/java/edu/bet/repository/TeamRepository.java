package edu.bet.repository;

import edu.bet.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    Optional<Team> findByName(String teamName);

    boolean existsByName(String teamName);

    void deleteByName(String teamName);

}
