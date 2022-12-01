package edu.bet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "maps")
@Getter
@Setter
public class Map {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "win_rate", nullable = false)
    private Double winRate;

    @Column(name = "team", nullable = false)
    private String teamName;

    //---------------------------------------------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Team team;
}
