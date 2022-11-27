package edu.bet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "players")
@Entity
@Getter
@Setter
public class Player {

    @Id
    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "kd", nullable = false)
    private Double kd;

    @Column(name = "kr", nullable = false)
    private Double kr;

    //---------------------------------------------------------------------------------

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Team team;

}
