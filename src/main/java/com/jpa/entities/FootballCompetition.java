package com.jpa.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FootballCompetition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition="VARCHAR(100)")
    private String name;
    private Integer quantityPrice;
    @Column(updatable=false, columnDefinition="DATE", nullable=false)
    private LocalDate startDate;
    @Column(updatable=false, columnDefinition="DATE")
    private LocalDate endDate;

}
