package edu.bet.repository;

import edu.bet.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select m from Match as m where m.teamName1=:teamName or m.teamName2 = :teamName")
    Optional<Match> findMatchByTeam(@Param("teamName") String team);
}
