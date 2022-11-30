package edu.bet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "matches")
@Getter
@Setter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_1", nullable = false)
    private String teamName1;

    @Column(name = "team_2", nullable = false)
    private String teamName2;

    @Column(name = "first_map")
    private String firstMap;
    @Column(name = "second_map")
    private String secondMap;
    @Column(name = "third_map")
    private String thirdMap;

    @Column(name = "prediction_winner", nullable = false)
    private String prediction;

    @Column(name = "winner", nullable = false)
    private String winner;

}
