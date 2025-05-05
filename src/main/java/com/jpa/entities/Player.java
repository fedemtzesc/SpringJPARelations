package com.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name="last_name")
    private String lastName;
    private String nationality;
    private Integer age;
    private String position;

    @ManyToOne(targetEntity=Club.class)
    private Club club;

}
