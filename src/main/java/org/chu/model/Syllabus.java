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
// учебный план
public class Syllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long syllabus_id;
    @ManyToOne
    @JoinColumn(name = "specially_id", nullable = false)
    private Specially specially;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;
    private int semester;
    private int countHours;
    @Enumerated(EnumType.ORDINAL)
    private ReportingForm reportingForm;

    public Syllabus(Specially specially, Discipline discipline, int semester, int countHours, ReportingForm reportingForm) {
        this.specially = specially;
        this.discipline = discipline;
        this.semester = semester;
        this.countHours = countHours;
        this.reportingForm = reportingForm;
    }
}
