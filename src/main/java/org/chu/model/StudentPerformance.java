package org.chu.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
// журналуспеваемости студентов
public class StudentPerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_performance_id;
    private int semester;
    private int year;
    @ManyToOne
    @JoinColumn( nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Discipline discipline;
    private int rating;

    public StudentPerformance(int semester, int year, Student student, Discipline discipline, int rating) {
        this.semester = semester;
        this.year = year;
        this.student = student;
        this.discipline = discipline;
        this.rating = rating;
    }
}
