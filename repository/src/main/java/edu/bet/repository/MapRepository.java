package edu.bet.repository;

import edu.bet.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<Map, String> {

    Map findByName(String name);

    void deleteByName(String name);

    boolean existsByName(String name);

}
