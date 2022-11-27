package edu.bet.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "win_rate", nullable = false)
    private Double winRate;

    //---------------------------------------------------------------------------------

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Player> players;


    @ManyToMany
    @JoinTable(
            name = "map_pull",
            joinColumns = @JoinColumn(name = "team_name"),
            inverseJoinColumns = @JoinColumn(name = "map_name"),
            foreignKey = @ForeignKey(name = "fk_team_to_map"))
    List<Map> mapPull;

}
