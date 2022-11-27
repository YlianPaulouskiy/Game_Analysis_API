package edu.bet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "maps")
@Getter
@Setter
public class Map {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "win_rate", nullable = false)
    private Double winRate;

    //---------------------------------------------------------------------------------

    @ManyToMany
    @JoinTable(
            name = "map_pull",
            joinColumns = @JoinColumn(name = "map_name"),
            inverseJoinColumns = @JoinColumn(name = "team_name"),
            foreignKey = @ForeignKey(name = "fk_map_to_team"))
    private List<Team> teamList;
}
