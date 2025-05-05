package com.jpa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // con PERSIST, provocamos que al momento de guardar el club, tambien se guarde
    // el coach en la bd
    @OneToOne(targetEntity = Coach.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_coach") // Con esto le indicamos a JPA que cambie el nombre de la FK de coach
    private Coach coach;

    /**
     * ******************************** NOTAS
     * *************************************************************
     * - FetchType.EAGER: se va a traer todo el listado de jugadores cuando cargue
     * el club
     * - FetchType.LAZY: se va a traer el listado de jugadores si se los solicito
     * implicitamente con el get
     * - Al poner mappedBy con el nombre del campo de la tabla que pertenece al
     * "muchos", se estaria
     * creando una tabla "intermedia" con los id de cada tabla, y entonces no seria
     * una relacion de
     * one-to-many entre las dos tablas. Esto es, que el id del club, pasa como FK a
     * la tabla de Player
     */
    @OneToMany(targetEntity = Player.class, fetch = FetchType.LAZY, mappedBy = "club")
    private List<Player> playerList = new ArrayList<>();

    @ManyToOne(targetEntity = FootballAssociation.class)
    private FootballAssociation footballAssociation;

    /**
     * Para modificar el nombre de la tabla de asociacion de many-to-many usamos la anotacion @JoinTable
     * Y si necesitamos cambiar los nombres de las dos columnas de la asociacion usamos los parametros de
     * configuracion que se muestran dentro de esta anotacion. 
     * inverseJoinColumns se refiere a la columna del lado opuesto de la relacion.
     */
    @ManyToMany(targetEntity = FootballCompetition.class, fetch = FetchType.LAZY)
    @JoinTable(name = "club_competition", joinColumns = @JoinColumn(name = "fk_club_id"), inverseJoinColumns = @JoinColumn(name = "fk_competition_id"))
    private List<FootballCompetition> competitions;

}
