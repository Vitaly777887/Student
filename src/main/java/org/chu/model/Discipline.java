package org.chu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discipline_id;
    private String name;

    @OneToMany(mappedBy = "discipline")
    private Set<StudentPerformance> studentPerformances;

    @OneToMany(mappedBy = "discipline")
    private Set<Syllabus> syllabuses;

}
